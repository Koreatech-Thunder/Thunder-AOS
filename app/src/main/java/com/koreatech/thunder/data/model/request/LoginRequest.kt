package com.koreatech.thunder.data.model.request

data class LoginRequest(
    val kakaoToken: String,
    val fcmToken: String
)
