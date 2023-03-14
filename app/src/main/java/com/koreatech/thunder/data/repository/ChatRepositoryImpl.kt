package com.koreatech.thunder.data.repository

import com.koreatech.thunder.domain.model.ChatRoom
import com.koreatech.thunder.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor() : ChatRepository {
    override suspend fun getChatRooms(): Result<List<ChatRoom>> {
        TODO("Not yet implemented")
    }
}
