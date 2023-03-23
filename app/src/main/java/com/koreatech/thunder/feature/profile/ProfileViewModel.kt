package com.koreatech.thunder.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.SplashState
import com.koreatech.thunder.domain.model.User
import com.koreatech.thunder.domain.usecase.DeleteTokensUseCase
import com.koreatech.thunder.domain.usecase.GetUserProfileUseCase
import com.koreatech.thunder.domain.usecase.PostLogoutUseCase
import com.koreatech.thunder.domain.usecase.SetSplashStateUseCase
import com.koreatech.thunder.domain.usecase.WithdrawUserUseCase
import com.koreatech.thunder.navigation.ThunderDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val setSplashStateUseCase: SetSplashStateUseCase,
    private val withdrawUserUseCase: WithdrawUserUseCase,
    private val postLogoutUseCase: PostLogoutUseCase,
    private val deleteTokensUseCase: DeleteTokensUseCase
) : ViewModel() {
    private val _moveDestination = MutableSharedFlow<ThunderDestination>()
    private val _user: MutableStateFlow<User> = MutableStateFlow(
        User(
            userId = "",
            name = "",
            introduction = "",
            temperature = 36,
            hashtags = emptyList()
        )
    )
    val moveDestination = _moveDestination.asSharedFlow()
    val user = _user.asStateFlow()

    init {
        getUserProfile()
    }

    fun getUserProfile() {
        viewModelScope.launch {
            getUserProfileUseCase()
                .onSuccess { user -> _user.value = user }
                .onFailure { Timber.e("error ${it.message}") }
        }
    }

    fun setSplashState(splashState: SplashState) {
        setSplashStateUseCase(splashState)
    }

    fun withdrawUser() {
        viewModelScope.launch {
            withdrawUserUseCase()
                .onSuccess {
                    _moveDestination.emit(ThunderDestination.LOGIN)
                    deleteTokensUseCase()
                }
                .onFailure {
                    Timber.e("error ${it.message}")
                }
        }
    }

    fun postLogout() {
        viewModelScope.launch {
            postLogoutUseCase()
                .onSuccess {
                    _moveDestination.emit(ThunderDestination.LOGIN)
                    deleteTokensUseCase()
                }
                .onFailure {
                    Timber.e("error ${it.message}")
                }
        }
    }
}
