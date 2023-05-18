package com.koreatech.thunder.data.service

import com.koreatech.thunder.data.model.request.EvaluateRequest
import com.koreatech.thunder.data.model.response.EvaluateThunderResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface EvaluateService {
    @GET("/evaluate/{thunderId}")
    suspend fun getEvaluateUsers(
        @Path("thunderId") thunderId: String
    ): EvaluateThunderResponse

    @PUT("/evaluate/{thunderId}")
    suspend fun putEvaluates(
        @Path("thunderId") thunderId: String,
        @Body body: EvaluateRequest
    )
}