package com.koreatech.thunder.data.repository

import com.koreatech.thunder.data.source.remote.ChatDataSource
import com.koreatech.thunder.domain.model.ChatRoom
import com.koreatech.thunder.domain.model.ChatRoomDetail
import com.koreatech.thunder.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatDataSource: ChatDataSource
) : ChatRepository {
    override suspend fun getChatRooms(): Result<List<ChatRoom>> =
        runCatching { chatDataSource.getChatRooms() }

    override suspend fun getChatRoomDetail(chatId: String): Result<ChatRoomDetail> =
        runCatching { chatDataSource.getChatRoomDetail(chatId) }

    override suspend fun setChatRoomAlarm(thunderId: String, isAlarm: Boolean): Result<Unit> =
        runCatching { chatDataSource.setChatRoomAlarm(thunderId, isAlarm) }
}
