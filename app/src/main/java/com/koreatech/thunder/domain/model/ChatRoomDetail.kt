package com.koreatech.thunder.domain.model

data class ChatRoomDetail(
    val title: String,
    val limitMemberCnt: Int,
    val joinMemberCnt: Int,
    val chats: List<Chat>,
    val isAlarm: Boolean
)
