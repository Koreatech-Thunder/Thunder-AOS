package com.koreatech.thunder.data.model.response

import com.koreatech.thunder.domain.model.ChatUser

data class ChatUserResponse(
    val userId: String,
    val name: String
) {
    fun toChatUser() = ChatUser(
        userId = userId,
        name = name
    )
}
