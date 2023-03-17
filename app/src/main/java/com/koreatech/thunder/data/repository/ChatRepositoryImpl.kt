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

    override suspend fun getChatRoomDetail(): Result<ChatRoomDetail> =
        runCatching { chatDataSource.getChatRoomDetail() }

    override suspend fun setChatRoomAlarm(isAlarm: Boolean): Result<Unit> =
        runCatching { chatDataSource.setChatRoomAlarm(isAlarm) }
}
