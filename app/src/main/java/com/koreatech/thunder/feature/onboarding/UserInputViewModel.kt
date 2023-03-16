package com.koreatech.thunder.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.model.SplashState
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import com.koreatech.thunder.domain.usecase.PutUserProfileUseCase
import com.koreatech.thunder.domain.usecase.SetSplashStateUseCase
import com.koreatech.thunder.navigation.ThunderDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInputViewModel @Inject constructor(
    private val getAllSelectableHashtagUseCase: GetAllSelectableHashtagUseCase,
    private val setSplashStateUseCase: SetSplashStateUseCase,
    private val putUserProfileUseCase: PutUserProfileUseCase
) : ViewModel() {
    private val _moveDestination = MutableSharedFlow<ThunderDestination>()
    private val _hashtags: MutableStateFlow<List<SelectableHashtag>> = MutableStateFlow(emptyList())
    private val _nickname: MutableStateFlow<String> = MutableStateFlow("")
    val hashtags = _hashtags.asStateFlow()
    val nickname = _nickname.asStateFlow()
    val moveDestination = _moveDestination.asSharedFlow()

    init {
        _hashtags.value = getAllSelectableHashtagUseCase()
    }

    fun selectHashtag(index: Int) {
        _hashtags.value = _hashtags.value.mapIndexed { idx, selectableHashtag ->
            if (idx == index) selectableHashtag.copy(isSelected = !selectableHashtag.isSelected)
            else selectableHashtag
        }
    }

    fun writeNickname(nickname: String) {
        _nickname.value = nickname
    }

    private fun selectedHashtags(): List<String> =
        mutableListOf<String>().apply {
            hashtags.value.forEach { selectedHashtag ->
                if (selectedHashtag.isSelected) add(
                    selectedHashtag.hashtag.name
                )
            }
        }

    fun setSplashState(splashState: SplashState) {
        setSplashStateUseCase(splashState)
    }

    fun putUserProfile() {
        viewModelScope.launch {
            putUserProfileUseCase(
                name = nickname.value,
                introduction = "",
                hashtags = hashtags.value.map { it.hashtag }
            ).onSuccess {
                _moveDestination.emit(ThunderDestination.ON_BOARDING)
            }
                .onFailure { }
        }
    }
}
