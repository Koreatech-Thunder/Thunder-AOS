package com.koreatech.thunder.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.User
import com.koreatech.thunder.domain.usecase.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class ProfileViewModel @Inject constructor(
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
                        hashtags = user.hashtags
                    )
                }
                .onFailure { Timber.e("error ${it.message}") }
        }
    }
}
