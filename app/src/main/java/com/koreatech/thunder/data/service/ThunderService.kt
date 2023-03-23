package com.koreatech.thunder.data.service

import com.koreatech.thunder.data.model.ThunderResponse
import com.koreatech.thunder.data.model.request.ThunderRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

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
    ): ThunderResponse

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
}
