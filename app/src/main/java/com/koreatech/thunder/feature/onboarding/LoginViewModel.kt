package com.koreatech.thunder.feature.onboarding

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.SplashState
import com.koreatech.thunder.domain.repository.AuthRepository
import com.koreatech.thunder.domain.usecase.SetSplashStateUseCase
import com.koreatech.thunder.navigation.ThunderDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val setSplashStateUseCase: SetSplashStateUseCase
) : ViewModel() {
    private val kakaoToken = MutableStateFlow("")
    private val fcmToken = MutableStateFlow("")
    private val _moveDestination = MutableSharedFlow<ThunderDestination>()
    val moveDestination = _moveDestination.asSharedFlow()

    fun postLogin(context: Context) {
        viewModelScope.launch {
            val job1 = getKakaoToken(context)
            val job2 = getFCMToken()
            joinAll(job1, job2)
            authRepository.postLogin(
                kakaoToken = kakaoToken.value,
                fcmToken = fcmToken.value
            ).onSuccess { tokens ->
                authRepository.setTokens(
                    accessToken = tokens.accessToken,
                    refreshToken = tokens.refreshToken
                )
                setSplashStateUseCase(SplashState.USER_INPUT)
                _moveDestination.emit(ThunderDestination.USER_INPUT)
            }.onFailure { throwable ->
                if (throwable is HttpException) {
                    when (throwable.code()) {
                        USER_CONFLICT -> {
                            authRepository.postExistLogout(
                                kakaoToken = kakaoToken.value,
                                fcmToken = fcmToken.value
                            ).onSuccess { tokens ->
                                authRepository.setTokens(
                                    accessToken = tokens.accessToken,
                                    refreshToken = tokens.refreshToken
                                )
                                setSplashStateUseCase(SplashState.MAIN)
                                _moveDestination.emit(ThunderDestination.THUNDER)
                            }.onFailure { Timber.e("error ${throwable.message}") }
                        }
                        USER_INPUT -> {
                            authRepository.postExistLogout(
                                kakaoToken = kakaoToken.value,
                                fcmToken = fcmToken.value
                            ).onSuccess { tokens ->
                                authRepository.setTokens(
                                    accessToken = tokens.accessToken,
                                    refreshToken = tokens.refreshToken
                                )
                                setSplashStateUseCase(SplashState.USER_INPUT)
                                _moveDestination.emit(ThunderDestination.USER_INPUT)
                            }.onFailure { Timber.e("error ${throwable.message}") }
                        }
                    }
                }
                Timber.e("error ${throwable.message}")
            }
        }
    }

    private fun getKakaoToken(context: Context) = viewModelScope.launch {
        authRepository.getKakaoToken(context).onSuccess { token ->
            kakaoToken.value = token.accessToken
        }.onFailure {
            Timber.e("kakao error: ${it.message}")
        }
    }

    private fun getFCMToken() = viewModelScope.launch {
        authRepository.getFCMToken().onSuccess { token ->
            fcmToken.value = token
        }.onFailure {
            Timber.e("fcm error: ${it.message}")
        }
    }

    companion object {
        const val USER_CONFLICT = 409
        const val USER_INPUT = 400
    }
}
