package com.koreatech.thunder.domain.model

data class ChatRoom(
    val id: String,
    val title: String,
    val limitMemberCnt: Int,
    val joinMemberCnt: Int,
    val endTime: String,
    val lastChat: Chat?
)
