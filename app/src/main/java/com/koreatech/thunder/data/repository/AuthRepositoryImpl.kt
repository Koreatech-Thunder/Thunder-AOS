package com.koreatech.thunder.data.repository

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.koreatech.thunder.data.source.local.AuthLocalDataSource
import com.koreatech.thunder.data.source.remote.AuthRemoteDataSource
import com.koreatech.thunder.domain.model.SplashState
import com.koreatech.thunder.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override fun setSplashState(splashState: SplashState) {
        authLocalDataSource.splashState = splashState.toString()
    }

    override suspend fun postLogin(
        kakaoToken: String,
        fcmToken: String
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getKakaoToken(context: Context): Result<OAuthToken> =
        runCatching { authRemoteDataSource.kakaoLogin(context) }

    override suspend fun getFCMToken(): Result<String> =
        runCatching { authRemoteDataSource.getFCMToken() }

    override fun getSplashState(): SplashState =
        SplashState.valueOf(authLocalDataSource.splashState)
}
