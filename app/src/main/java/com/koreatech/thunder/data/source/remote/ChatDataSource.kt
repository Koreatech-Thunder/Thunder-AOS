package com.koreatech.thunder.data.source.remote

import com.koreatech.thunder.data.model.request.ChatAlarmRequest
import com.koreatech.thunder.data.service.ChatService
import com.koreatech.thunder.domain.model.ChatRoom
import com.koreatech.thunder.domain.model.ChatRoomDetail
import javax.inject.Inject

class ChatDataSource @Inject constructor(
    private val chatService: ChatService
) {
    suspend fun getChatRooms(): List<ChatRoom> =
        chatService.getChatRooms()

    suspend fun getChatRoomDetail(chatId: String): ChatRoomDetail =
        chatService.getChatRoomDetail(chatId)

    suspend fun setChatRoomAlarm(isAlarm: Boolean) =
        chatService.setChatRoomAlarm(ChatAlarmRequest(isAlarm))
}
