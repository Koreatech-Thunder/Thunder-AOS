package com.koreatech.thunder.data.model.response

import com.koreatech.thunder.domain.model.ChatRoom
import com.koreatech.thunder.domain.model.ChatRoomState

data class ChatRoomResponse(
    val id: String,
    val title: String,
    val limitMemberCnt: Int,
    val joinMemberCnt: Int,
    val endTime: String,
    val lastChat: ChatResponse?,
    val chatRoomState: String
) {
    fun toChatRoom() = ChatRoom(
        id = id,
        title = title,
        limitMemberCnt = limitMemberCnt,
        joinMemberCnt = joinMemberCnt,
        endTime = endTime,
        lastChat = lastChat?.toChat(id),
        chatRoomState = ChatRoomState.valueOf(chatRoomState)
    )
}