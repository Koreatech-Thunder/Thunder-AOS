package com.koreatech.thunder.data.model.response

import com.koreatech.thunder.domain.model.ProfileType
import com.koreatech.thunder.domain.model.User

data class EvaluateUserResponse(
    val userId: String,
    val name: String,
    val profile: String
) {
    fun toUser() = User(
        userId = userId,
        name = name,
        introduction = "",
        temperature = 0,
        hashtags = emptyList(),
        profile = ProfileType.valueOf(profile)
    )
}
