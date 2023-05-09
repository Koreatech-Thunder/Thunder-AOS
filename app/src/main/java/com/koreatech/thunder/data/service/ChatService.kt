package com.koreatech.thunder.data.service

import com.koreatech.thunder.data.model.request.ChatAlarmRequest
import com.koreatech.thunder.data.model.request.ChatReportRequest
import com.koreatech.thunder.data.model.response.ChatRoomDetailResponse
import com.koreatech.thunder.data.model.response.ChatRoomResponse
import retrofit2.http.*

interface ChatService {
    @GET("/chat")
    suspend fun getChatRooms(): List<ChatRoomResponse>

    @GET("chat/{thunderId}")
    suspend fun getChatRoomDetail(
        @Path("thunderId") thunderId: String
    ): ChatRoomDetailResponse

    @PUT("chat/{thunderId}/alarm")
    suspend fun setChatRoomAlarm(
        @Path("thunderId") thunderId: String,
        @Body body: ChatAlarmRequest
    )

    @POST("report/chat/{thunderId}")
    suspend fun reportChat(
        @Path("thunderId") thunderId: String,
        @Body body: ChatReportRequest
    )
}
