package com.koreatech.thunder.data.source.remote

import com.koreatech.thunder.data.model.request.ChatAlarmRequest
import com.koreatech.thunder.data.model.response.ChatRoomDetailResponse
import com.koreatech.thunder.data.model.response.ChatRoomResponse
import com.koreatech.thunder.data.service.ChatService
import javax.inject.Inject

class ChatDataSource @Inject constructor(
    private val chatService: ChatService
) {
    suspend fun getChatRooms(): List<ChatRoomResponse> =
        chatService.getChatRooms()

    suspend fun getChatRoomDetail(chatId: String): ChatRoomDetailResponse =
        chatService.getChatRoomDetail(chatId)

    suspend fun setChatRoomAlarm(thunderId: String, isAlarm: Boolean) =
        chatService.setChatRoomAlarm(thunderId, ChatAlarmRequest(isAlarm))
}
