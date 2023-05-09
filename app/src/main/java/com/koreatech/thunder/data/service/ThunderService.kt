package com.koreatech.thunder.data.service

import com.koreatech.thunder.data.model.request.ThunderReportRequest
import com.koreatech.thunder.data.model.request.ThunderRequest
import com.koreatech.thunder.data.model.response.ThunderDetailResponse
import com.koreatech.thunder.data.model.response.ThunderResponse
import retrofit2.http.*

interface ThunderService {
    @GET("thunder")
    suspend fun getThunders(): List<ThunderResponse>

    @GET("thunder/hashtags")
    suspend fun getThundersWithHashtag(
        @Query("hashtag") hashtag: String
    ): List<ThunderResponse>

    @GET("thunder/{thunderId}")
    suspend fun getThunder(
        @Path("thunderId") thunderId: String
    ): ThunderDetailResponse

    @POST("thunder")
    suspend fun postThunder(
        @Body body: ThunderRequest
    )

    @PUT("thunder/{thunderId}")
    suspend fun editThunder(
        @Path("thunderId") thunderId: String,
        @Body body: ThunderRequest
    )

    @PUT("thunder/join/{thunderId}")
    suspend fun joinThunder(
        @Path("thunderId") thunderId: String
    )

    @PUT("thunder/out/{thunderId}")
    suspend fun outThunder(
        @Path("thunderId") thunderId: String
    )

    @POST("report/thunder/{thunderId}")
    suspend fun reportThunder(
        @Path("thunderId") thunderId: String,
        @Body body: ThunderReportRequest
    )
}
