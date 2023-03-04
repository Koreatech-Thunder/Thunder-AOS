package com.koreatech.thunder.feature.onboarding

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.SplashState
import com.koreatech.thunder.domain.repository.AuthRepository
import com.koreatech.thunder.domain.usecase.SetSplashStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val setSplashStateUseCase: SetSplashStateUseCase
) : ViewModel() {
    private val kakaoToken = MutableStateFlow("")
    private val fcmToken = MutableStateFlow("")

    fun postLogin(context: Context) {
        viewModelScope.launch {
            val job1 = getKakaoToken(context)
            val job2 = getFCMToken()
            joinAll(job1, job2)
            authRepository.postLogin(
                kakaoToken = kakaoToken.value,
                fcmToken = fcmToken.value
            )
                .onSuccess {
                    // 로그인 성공 후 access token, refresh token 저장
                    // authRepository.setTokens()
                }
                .onFailure { }
        }
    }

    fun getKakaoToken(context: Context) =
        viewModelScope.launch {
            authRepository.getKakaoToken(context)
                .onSuccess { token ->
                    kakaoToken.value = token.accessToken
                }
                .onFailure {
                    Timber.e("kakao error: ${it.message}")
                }
        }

    fun getFCMToken() =
        viewModelScope.launch {
            authRepository.getFCMToken()
                .onSuccess { token ->
                    fcmToken.value = token
                }
                .onFailure {
                    Timber.e("fcm error: ${it.message}")
                }
        }

    fun setSplashState(splashState: SplashState) {
        setSplashStateUseCase(splashState)
    }
}
