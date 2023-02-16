package com.koreatech.thunder.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.model.User
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import com.koreatech.thunder.domain.usecase.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val getAllSelectableHashtagUseCase: GetAllSelectableHashtagUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {
    private val _user: MutableStateFlow<User> = MutableStateFlow(
        User(
            userId = "",
            name = "",
            introduction = "",
            temperature = 0,
            hashtags = emptyList()
        )
    )
    val user = _user.asStateFlow()

    fun getUserProfile() {
        viewModelScope.launch {
            getUserProfileUseCase()
                .onSuccess { user ->
                    _user.value = _user.value.copy(
                        userId = user.userId,
                        name = user.name,
                        introduction = user.introduction,
                        temperature = user.temperature,
                        hashtags = getAllSelectableHashtagUseCase(user.hashtags)
                    )
                }
                .onFailure { Timber.e("error ${it.message}") }
        }
    }

    fun selectHashtag(index: Int) {
        val afterHashtags = _user.value.hashtags.mapIndexed { idx, selectableHashtag ->
            if (idx == index) selectableHashtag.copy(isSelected = !selectableHashtag.isSelected)
            else selectableHashtag
        }
        _user.value = _user.value.copy(hashtags = afterHashtags)
    }

    private fun setUserHashtags(userHashtags: List<SelectableHashtag>) {
    }
}
