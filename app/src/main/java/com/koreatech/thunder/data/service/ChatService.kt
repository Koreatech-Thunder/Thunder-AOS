package com.koreatech.thunder.data.service

import com.koreatech.thunder.data.model.request.ChatAlarmRequest
import com.koreatech.thunder.domain.model.ChatRoom
import com.koreatech.thunder.domain.model.ChatRoomDetail
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ChatService {
    @GET("/chat")
    suspend fun getChatRooms(): List<ChatRoom>

    @GET("chat/{thunderId}")
    suspend fun getChatRoomDetail(
        @Path("thunderId") thunderId: String
    ): ChatRoomDetail

    @PUT("chat/{thunderId}/alarm")
    suspend fun setChatRoomAlarm(
        @Path("thunderId") thunderId: String,
        @Body body: ChatAlarmRequest
    )
}
