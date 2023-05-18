package com.koreatech.thunder.data.service

import com.koreatech.thunder.data.model.response.EvaluateThunderResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EvaluateService {
    @GET("/evaluate/{thunderId}")
    suspend fun getEvaluateUsers(
        @Path("thunderId") thunderId: String,
    ): EvaluateThunderResponse
}