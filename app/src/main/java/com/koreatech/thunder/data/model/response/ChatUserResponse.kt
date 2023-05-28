package com.koreatech.thunder.data.model.response

import com.koreatech.thunder.domain.model.ChatUser
import com.koreatech.thunder.domain.model.ProfileType

data class ChatUserResponse(
    val userId: String,
    val name: String,
    val profile: String
) {
    fun toChatUser() = ChatUser(
        userId = userId,
        name = name,
        profile = ProfileType.valueOf(profile)
    )
}
