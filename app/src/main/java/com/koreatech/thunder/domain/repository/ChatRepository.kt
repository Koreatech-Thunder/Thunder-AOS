package com.koreatech.thunder.domain.repository

import com.koreatech.thunder.domain.model.ChatRoom
import com.koreatech.thunder.domain.model.ChatRoomDetail

interface ChatRepository {
    suspend fun getChatRooms(): Result<List<ChatRoom>>
    suspend fun getChatRoomDetail(): Result<ChatRoomDetail>
    suspend fun setChatRoomAlarm(isAlarm: Boolean): Result<Unit>
}
