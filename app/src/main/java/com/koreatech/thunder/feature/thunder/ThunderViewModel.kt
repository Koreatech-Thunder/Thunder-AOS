package com.koreatech.thunder.feature.thunder

import androidx.lifecycle.ViewModel
import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ThunderViewModel @Inject constructor() : ViewModel() {
    private val _thunderUiState: MutableStateFlow<ThunderUiState> =
        MutableStateFlow(ThunderUiState.Loading)
    private val _hashtagUiState: MutableStateFlow<HashtagUiState> =
        MutableStateFlow(HashtagUiState.Loading)
    private val _userInfo = MutableSharedFlow<User>()
    private val _isError: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val thunderUiState = _thunderUiState.asStateFlow()
    val hashtagUiState = _hashtagUiState.asStateFlow()
    val userInfo = _userInfo.asSharedFlow()
    val isError = _isError.asSharedFlow()

    fun getThunders() {
    }

    fun getHashTaggedThunders() {
    }

    fun getUser() {
    }

    fun enterThunder(thunderId: String, userId: String) {
    }

    fun cancelThunder(thunderId: String, userId: String) {
    }

    fun getHashtags() {
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
