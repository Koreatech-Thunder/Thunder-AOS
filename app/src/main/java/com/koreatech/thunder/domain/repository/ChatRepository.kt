package com.koreatech.thunder.domain.repository

import com.koreatech.thunder.domain.model.ChatRoom
import com.koreatech.thunder.domain.model.ChatRoomDetail

interface ChatRepository {
    suspend fun getChatRooms(): Result<List<ChatRoom>>
    suspend fun getChatRoomDetail(chatId: String): Result<ChatRoomDetail>
    suspend fun setChatRoomAlarm(thunderId: String, isAlarm: Boolean): Result<Unit>
}
