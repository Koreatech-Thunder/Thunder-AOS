package com.koreatech.thunder.data.service

import com.koreatech.thunder.data.model.request.LoginRequest
import com.koreatech.thunder.data.model.response.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/login")
    suspend fun postLogin(
        @Body body: LoginRequest
    ): TokenResponse

    @POST("auth/logout")
    suspend fun postLogout()

    @POST("auth/refresh")
    suspend fun postRefreshToken(
        @Body tokens: TokenResponse
    ): TokenResponse
}
