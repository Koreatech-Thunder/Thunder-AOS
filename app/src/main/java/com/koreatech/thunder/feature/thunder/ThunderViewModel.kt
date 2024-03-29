package com.koreatech.thunder.feature.thunder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.*
import com.koreatech.thunder.domain.repository.ThunderRepository
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import com.koreatech.thunder.domain.usecase.GetUserHashtagsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ThunderViewModel @Inject constructor(
    private val thunderRepository: ThunderRepository,
    private val getAllSelectableHashtagUseCase: GetAllSelectableHashtagUseCase,
    private val getUserHashtagsUseCase: GetUserHashtagsUseCase
) : ViewModel() {
    private val reportThunder: MutableStateFlow<String> = MutableStateFlow("")
    private val _thunderUiState: MutableStateFlow<ThunderUiState> =
        MutableStateFlow(ThunderUiState.Loading)
    private val _hashtagUiState: MutableStateFlow<HashtagUiState> =
        MutableStateFlow(HashtagUiState.Loading)
    private val _userInfo = MutableStateFlow(dummyUsers[0])
    private val _uiEvent: MutableSharedFlow<UiEvent> = MutableSharedFlow()
    val thunderUiState = _thunderUiState.asStateFlow()
    val hashtagUiState = _hashtagUiState.asStateFlow()
    val userInfo = _userInfo.asStateFlow()
    val uiEvent = _uiEvent.asSharedFlow()

    fun getThunders() {
        viewModelScope.launch {
            thunderRepository.getThunders()
                .onSuccess { thunders ->
                    if (thunders.isEmpty()) _thunderUiState.value = ThunderUiState.Empty
                    else _thunderUiState.value = ThunderUiState.Success(thunders)
                }
                .onFailure {
                    Timber.e("error is  ${it.message}")
                    _uiEvent.emit(UiEvent.NETWORK_FAIL)
                }
        }
    }

    private fun getThundersWithHashtag(hashtag: Hashtag) {
        viewModelScope.launch {
            thunderRepository.getThundersWithHashtag(hashtag)
                .onSuccess { thunders ->
                    if (thunders.isEmpty()) _thunderUiState.value = ThunderUiState.Empty
                    else _thunderUiState.value = ThunderUiState.Success(thunders)
                }
                .onFailure {
                    Timber.e("error is  ${it.message}")
                    _uiEvent.emit(UiEvent.NETWORK_FAIL)
                }
        }
    }

    fun joinThunder(thunder: Thunder) {
        viewModelScope.launch {
            if (thunder.participants.size < thunder.limitParticipantsCnt) {
                thunderRepository.joinThunder(thunder.thunderId)
                    .onSuccess {
                        (hashtagUiState.value as HashtagUiState.Success).hashtags.forEach { selectedHashtag ->
                            if (selectedHashtag.isSelected) {
                                getThundersWithHashtag(selectedHashtag.hashtag)
                                return@onSuccess
                            }
                        }
                        getThunders()
                        _uiEvent.emit(UiEvent.JOIN_SUCCESS)
                    }
                    .onFailure {
                        Timber.e("error is ${it.message}")
                        _uiEvent.emit(UiEvent.NETWORK_FAIL)
                    }
            }
        }
    }

    fun outThunder(thunderId: String) {
        viewModelScope.launch {
            thunderRepository.outThunder(thunderId)
                .onSuccess {
                    (hashtagUiState.value as HashtagUiState.Success).hashtags.forEach { selectedHashtag ->
                        if (selectedHashtag.isSelected) {
                            getThundersWithHashtag(selectedHashtag.hashtag)
                            return@onSuccess
                        }
                    }
                    getThunders()
                    _uiEvent.emit(UiEvent.OUT_SUCCESS)
                }
                .onFailure {
                    Timber.e("error is ${it.message}")
                    _uiEvent.emit(UiEvent.NETWORK_FAIL)
                }
        }
    }

    fun getHashtags() {
        viewModelScope.launch {
            getUserHashtagsUseCase()
                .onSuccess { hashtags ->
                    if (hashtags.isEmpty()) {
                        _hashtagUiState.value =
                            HashtagUiState.Success(getAllSelectableHashtagUseCase())
                    } else {
                        _hashtagUiState.value = HashtagUiState.Success(hashtags)
                    }
                }
                .onFailure {
                    Timber.e("error is  ${it.message}")
                }
        }
    }

    fun selectHashtag(index: Int) {
        val afterHashtags =
            (hashtagUiState.value as HashtagUiState.Success).hashtags.mapIndexed { idx, hashtag ->
                if (idx == index) {
                    if (hashtag.isSelected) {
                        getThunders()
                        hashtag.copy(isSelected = false)
                    } else {
                        getThundersWithHashtag((hashtagUiState.value as HashtagUiState.Success).hashtags[idx].hashtag)
                        hashtag.copy(isSelected = true)
                    }
                } else if (hashtag.isSelected) hashtag.copy(isSelected = false)
                else hashtag
            }
        _hashtagUiState.value = HashtagUiState.Success(afterHashtags)
    }

    fun setUser(user: User) {
        _userInfo.value = user
    }

    fun setReportThunder(thunderId: String) {
        reportThunder.value = thunderId
    }

    fun reportThunder(reportCategory: Int) {
        viewModelScope.launch {
            thunderRepository.reportThunder(reportThunder.value, reportCategory)
                .onSuccess {
                    _uiEvent.emit(UiEvent.REPORT_SUCCESS)
                }
                .onFailure {
                    _uiEvent.emit(UiEvent.NETWORK_FAIL)
                }
        }
    }

    companion object {
        const val SELECTABLE_COUNT = 1
    }
}

sealed interface ThunderUiState {
    object Loading : ThunderUiState
    object Empty : ThunderUiState
    data class Success(val thunders: List<Thunder>) : ThunderUiState
}

sealed interface HashtagUiState {
    object Loading : HashtagUiState
    object Error : HashtagUiState
    data class Success(val hashtags: List<SelectableHashtag>) : HashtagUiState
}

enum class UiEvent {
    REPORT_SUCCESS, JOIN_SUCCESS, OUT_SUCCESS, NETWORK_FAIL
}
