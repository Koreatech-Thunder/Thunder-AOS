package com.koreatech.thunder.data.source.remote

import android.content.Context
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.koreatech.thunder.data.model.request.LoginRequest
import com.koreatech.thunder.data.model.response.TokenResponse
import com.koreatech.thunder.data.service.AuthService
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import timber.log.Timber

class AuthRemoteDataSource @Inject constructor(
    private val authService: AuthService
) {
    suspend fun kakaoLogin(context: Context): OAuthToken =
        suspendCoroutine { continuation ->
            val kakaoCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    continuation.resumeWithException(error)
                } else if (token != null) {
                    continuation.resume(token)
                }
            }

            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(context, callback = kakaoCallback)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoCallback)
            }
        }

    suspend fun getFCMToken(): String =
        suspendCoroutine { continuation ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener(
                OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Timber.e("Fetching FCM registration token failed", task.exception)
                        continuation.resumeWithException(Throwable(task.exception))
                        return@OnCompleteListener
                    }
                    continuation.resume(task.result)
                }
            )
        }

    suspend fun postLogin(
        body: LoginRequest
    ): TokenResponse = authService.postLogin(body)

    suspend fun postLogout() = authService.postLogout()
}
