package com.koreatech.thunder.domain.model

data class Chat(
    val id: String,
    val thunderId: String,
    val user: User,
    val message: String,
    val createdAt: String,
    val state: ChatState
)
