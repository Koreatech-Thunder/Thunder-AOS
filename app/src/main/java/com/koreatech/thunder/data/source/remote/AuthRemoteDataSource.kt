package com.koreatech.thunder.data.source.remote

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AuthRemoteDataSource @Inject constructor() {
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
}
