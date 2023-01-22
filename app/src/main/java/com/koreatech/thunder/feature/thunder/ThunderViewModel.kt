package com.koreatech.thunder.feature.thunder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.domain.model.dummyUsers
import com.koreatech.thunder.domain.repository.ThunderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThunderViewModel @Inject constructor(
    private val thunderRepository: ThunderRepository
) : ViewModel() {
    private val _hashtagIndexState: MutableStateFlow<HashtagIndexState> =
        MutableStateFlow(HashtagIndexState.IDLE)
    private val _thunderUiState: MutableStateFlow<ThunderUiState> =
        MutableStateFlow(ThunderUiState.Loading)
    private val _hashtagUiState: MutableStateFlow<HashtagUiState> =
        MutableStateFlow(HashtagUiState.Loading)
    private val _userInfo = MutableStateFlow(dummyUsers[0])
    private val _isError: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val thunderUiState = _thunderUiState.asStateFlow()
    val hashtagUiState = _hashtagUiState.asStateFlow()
    val hashtagIndexState = _hashtagIndexState.asStateFlow()
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
                        _hashtagUiState.value = HashtagUiState.Success(Hashtag.values().toList())
                    } else {
                        _hashtagUiState.value = HashtagUiState.Success(hashtags)
                    }
                }
                .onFailure { }
        }
    }

    fun selectHashtag(index: Int) {
        if (hashtagIndexState.value is HashtagIndexState.SELECTED && (hashtagIndexState.value as HashtagIndexState.SELECTED).index == index) {
            _hashtagIndexState.value = HashtagIndexState.IDLE
            return
        }
        _hashtagIndexState.value = HashtagIndexState.SELECTED(index)
    }

    fun setUser(thunderIndex: Int, userIndex: Int) {
        _userInfo.value =
            (thunderUiState.value as ThunderUiState.Success).thunders[thunderIndex].participants[userIndex]
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
    data class Success(val hashtags: List<Hashtag>) : HashtagUiState
}

sealed interface HashtagIndexState {
    object IDLE : HashtagIndexState
    data class SELECTED(val index: Int) : HashtagIndexState
}
