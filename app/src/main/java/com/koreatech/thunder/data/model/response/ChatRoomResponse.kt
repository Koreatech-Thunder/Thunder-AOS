package com.koreatech.thunder.data.model.response

data class ChatRoomResponse(
    val id: String,
    val title: String,
    val limitMemberCnt: Int,
    val joinMemberCnt: Int,
    val endTime: String,
    val lastChat: ChatResponse?
)