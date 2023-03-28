package com.koreatech.thunder.data.repository

import android.annotation.SuppressLint
import com.koreatech.thunder.data.source.remote.ChatDataSource
import com.koreatech.thunder.domain.model.ChatRoom
import com.koreatech.thunder.domain.model.ChatRoomDetail
import com.koreatech.thunder.domain.model.ChatRoomState
import com.koreatech.thunder.domain.repository.ChatRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatDataSource: ChatDataSource
) : ChatRepository {
    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")

    override suspend fun getChatRooms(): Result<List<ChatRoom>> =
        runCatching { chatDataSource.getChatRooms() }
            .mapCatching { chatRooms ->
                chatRooms.map { chatRoom ->
                    val currentDate = Date()
                    val compareDate = dateFormat.parse(chatRoom.endTime)
                    val chatRoomState =
                        if (currentDate.after(compareDate)) ChatRoomState.EVALUATE else ChatRoomState.RUNNING
                    ChatRoom(
                        id = chatRoom.id,
                        title = chatRoom.title,
                        limitMemberCnt = chatRoom.limitMemberCnt,
                        joinMemberCnt = chatRoom.joinMemberCnt,
                        endTime = chatRoom.endTime,
                        lastChat = chatRoom.lastChat?.toChat(chatRoom.id),
                        chatRoomState = chatRoomState
                    )
                }
            }

    override suspend fun getChatRoomDetail(chatId: String): Result<ChatRoomDetail> =
        runCatching { chatDataSource.getChatRoomDetail(chatId).toChatRoomDetail() }

    override suspend fun setChatRoomAlarm(thunderId: String, isAlarm: Boolean): Result<Unit> =
        runCatching { chatDataSource.setChatRoomAlarm(thunderId, isAlarm) }
}
