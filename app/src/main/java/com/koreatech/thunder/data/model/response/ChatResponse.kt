package com.koreatech.thunder.data.model.response

import com.koreatech.thunder.domain.model.Chat
import com.koreatech.thunder.domain.model.ChatState
import com.koreatech.thunder.domain.model.dummyUsers

data class ChatResponse(
    val chatId: String,
    val user: ChatUserResponse,
    val message: String,
    val createdAt: String,
    val state: String
) {
    fun toChat(id: String) = Chat(
        id = chatId,
        thunderId = id,
        user = user.toChatUser(),
        message = message,
        createdAt = createdAt,
        state = ChatState.valueOf(state)
    )
}
