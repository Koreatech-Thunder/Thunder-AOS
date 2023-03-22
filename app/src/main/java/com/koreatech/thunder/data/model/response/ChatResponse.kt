package com.koreatech.thunder.data.model.response

import com.koreatech.thunder.domain.model.User

data class ChatResponse(
    val chatId: String,
    val user: User,
    val createdAt: String,
    val state: String
)
