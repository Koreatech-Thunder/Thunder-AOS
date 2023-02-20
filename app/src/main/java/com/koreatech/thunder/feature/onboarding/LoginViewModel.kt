package com.koreatech.thunder.feature.onboarding

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.SplashState
import com.koreatech.thunder.domain.repository.AuthRepository
import com.koreatech.thunder.domain.usecase.SetSplashStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val setSplashStateUseCase: SetSplashStateUseCase
) : ViewModel() {
    private val kakaoToken = MutableStateFlow("")
    fun postLogin(context: Context) {
        viewModelScope.launch {
            authRepository.getKakaoToken(context)
                .onSuccess { token ->
                    kakaoToken.value = token.accessToken
                }
                .onFailure {
                    Timber.e("error: ${it.message}")
                }
            val fcmToken = ""
//            authRepository.postLogin(
//                kakaoToken = kakaoToken.value,
//                fcmToken = fcmToken
//            )
//                .onSuccess { }
//                .onFailure { }
        }
    }

    fun setSplashState(splashState: SplashState) {
        setSplashStateUseCase(splashState)
    }
}
