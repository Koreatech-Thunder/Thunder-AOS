package com.koreatech.thunder.feature.thunder

import androidx.lifecycle.ViewModel
import com.koreatech.thunder.domain.repository.ThunderRepository
import com.koreatech.thunder.feature.thunder.model.HashtagUi
import com.koreatech.thunder.feature.thunder.model.ThunderUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ThunderViewModel @Inject constructor(
    private val thunderRepository: ThunderRepository
) : ViewModel() {
    private val _thunderUiState: MutableStateFlow<ThunderUiState> =
        MutableStateFlow(ThunderUiState.Loading)
    val thunderUiState = _thunderUiState.asStateFlow()
    private val _hashtagUiState: MutableStateFlow<HashtagUiState> =
        MutableStateFlow(HashtagUiState.Loading)
    val hashtagUiState = _hashtagUiState.asStateFlow()
    private val _isError: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isError = _isError.asSharedFlow()

    fun getThunders() {
    }

    fun getHashTaggedThunders() {
    }

    fun getUser() {
    }

    fun postThunder() {
    }

    fun enterThunder() {
    }

    fun cancelThunder() {
    }
}

sealed interface ThunderUiState {
    object Loading : ThunderUiState
    object Error : ThunderUiState
    data class Success(val thunders: List<ThunderUi>) : ThunderUiState
}

sealed interface HashtagUiState {
    object Loading : HashtagUiState
    object Error : HashtagUiState
    data class Success(val hashtags: List<HashtagUi>) : HashtagUiState
}
