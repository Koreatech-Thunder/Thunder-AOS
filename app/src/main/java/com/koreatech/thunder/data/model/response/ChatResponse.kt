package com.koreatech.thunder.data.model.response

import com.koreatech.thunder.domain.model.Chat
import com.koreatech.thunder.domain.model.ChatState

data class ChatResponse(
    val id: String,
    val user: ChatUserResponse,
    val message: String,
    val createdAt: String,
    val state: String
) {
    fun toChat(id: String) = Chat(
        id = this.id,
        thunderId = id,
        user = user.toChatUser(),
        message = message,
        createdAt = createdAt,
        state = ChatState.valueOf(state)
    )
}
