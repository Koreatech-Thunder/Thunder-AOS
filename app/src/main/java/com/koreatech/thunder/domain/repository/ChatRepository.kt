package com.koreatech.thunder.domain.repository

import com.koreatech.thunder.domain.model.ChatRoom

interface ChatRepository {
    suspend fun getChatRooms(): Result<List<ChatRoom>>
}
