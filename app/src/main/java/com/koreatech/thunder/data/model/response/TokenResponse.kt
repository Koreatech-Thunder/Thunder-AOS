package com.koreatech.thunder.data.model.response

import com.koreatech.thunder.domain.model.Tokens

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
) {
    fun toTokens() =
        Tokens(accessToken = accessToken, refreshToken = refreshToken)
}
