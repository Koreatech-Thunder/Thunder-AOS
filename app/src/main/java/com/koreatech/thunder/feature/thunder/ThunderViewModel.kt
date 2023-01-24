package com.koreatech.thunder.feature.thunder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.domain.model.User
import com.koreatech.thunder.domain.model.dummyUsers
import com.koreatech.thunder.domain.repository.ThunderRepository
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ThunderViewModel @Inject constructor(
    private val thunderRepository: ThunderRepository,
    private val getAllSelectableHashtagUseCase: GetAllSelectableHashtagUseCase
) : ViewModel() {
    private val _thunderUiState: MutableStateFlow<ThunderUiState> =
        MutableStateFlow(ThunderUiState.Loading)
    private val _hashtagUiState: MutableStateFlow<HashtagUiState> =
        MutableStateFlow(HashtagUiState.Success(getAllSelectableHashtagUseCase()))
    private val _userInfo = MutableStateFlow(dummyUsers[0])
    private val _isError: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val thunderUiState = _thunderUiState.asStateFlow()
    val hashtagUiState = _hashtagUiState.asStateFlow()
    val userInfo = _userInfo.asStateFlow()
    val isError = _isError.asSharedFlow()

    fun getThunders() {
        viewModelScope.launch {
            thunderRepository.getThunders()
                .onSuccess { thunders ->
                    _thunderUiState.value = ThunderUiState.Success(thunders)
                }
                .onFailure { }
        }
    }

    fun getHashTaggedThunders() {
    }

    fun getUser() {
    }

    fun enterThunder(thunder: Thunder) {
        viewModelScope.launch {
            if (thunder.participants.size < thunder.limitParticipantsCnt) {
                thunderRepository.enterThunder(thunder.thunderId)
            }
        }
    }

    fun cancelThunder(thunderId: String, userId: String) {
    }

    fun getHashtags() {
        viewModelScope.launch {
            thunderRepository.getHashtags()
                .onSuccess { hashtags ->
                    if (hashtags.isEmpty()) {
                        _hashtagUiState.value =
                            HashtagUiState.Success(getAllSelectableHashtagUseCase())
                    } else {
                        _hashtagUiState.value = HashtagUiState.Success(hashtags)
                    }
                }
                .onFailure { }
        }
    }

    fun selectHashtag(index: Int) {
        val afterHashtags =
            (hashtagUiState.value as HashtagUiState.Success).hashtags.mapIndexed { idx, hashtag ->
                if (idx == index) hashtag.copy(isSelected = !hashtag.isSelected)
                else if (hashtag.isSelected) hashtag.copy(isSelected = false)
                else hashtag
            }
        _hashtagUiState.value = HashtagUiState.Success(afterHashtags)
    }

    fun setUser(user: User) {
        _userInfo.value = user
    }

    companion object {
        const val SELECTABLE_COUNT = 1
    }
}

sealed interface ThunderUiState {
    object Loading : ThunderUiState
    object Error : ThunderUiState
    data class Success(val thunders: List<Thunder>) : ThunderUiState
}

sealed interface HashtagUiState {
    object Loading : HashtagUiState
    object Error : HashtagUiState
    data class Success(val hashtags: List<SelectableHashtag>) : HashtagUiState
}
