package com.koreatech.thunder.data.model.response

import com.google.gson.annotations.SerializedName
import com.koreatech.thunder.domain.model.ChatRoomDetail

data class ChatRoomDetailResponse(
    @SerializedName("thunderId") val id: String,
    val title: String,
    val limitMemberCnt: Int,
    val joinMemberCnt: Int,
    val chats: List<ChatResponse>,
    val isAlarm: Boolean
) {
    fun toChatRoomDetail() = ChatRoomDetail(
        id = id,
        title = title,
        limitMemberCnt = limitMemberCnt,
        joinMemberCnt = joinMemberCnt,
        chats = chats.map { chat -> chat.toChat(id) },
        isAlarm = isAlarm
    )
}
