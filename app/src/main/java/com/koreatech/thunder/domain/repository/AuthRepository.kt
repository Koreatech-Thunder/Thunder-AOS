package com.koreatech.thunder.domain.repository

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.koreatech.thunder.domain.model.SplashState
import com.koreatech.thunder.domain.model.Tokens

interface AuthRepository {
    suspend fun postLogin(
        kakaoToken: String,
        fcmToken: String
    ): Result<Tokens>

    suspend fun postLogout(): Result<Unit>

    suspend fun getKakaoToken(context: Context): Result<OAuthToken>
    suspend fun getFCMToken(): Result<String>
    fun getSplashState(): SplashState
    fun setSplashState(splashState: SplashState)
    fun setTokens(accessToken: String, refreshToken: String)
}
