package com.koreatech.thunder.feature.onboarding

import androidx.lifecycle.ViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.koreatech.thunder.data.repository.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import timber.log.Timber

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepositoryImpl
) : ViewModel() {
    fun handleKakao() {
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Timber.e("카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Timber.i("카카오계정으로 로그인 성공 ${token.accessToken}")
            }
        }
        authRepository.kakaoLogin(callback)
    }
}
