package com.koreatech.thunder.data.source.remote

import com.koreatech.thunder.data.model.request.EvaluateRequest
import com.koreatech.thunder.data.service.EvaluateService
import javax.inject.Inject

class EvaluateDataSource @Inject constructor(
    private val evaluateService: EvaluateService
) {

    suspend fun getEvaluateUsers(thunderId: String) =
        evaluateService.getEvaluateUsers(thunderId)

    suspend fun putEvaluate(thunderId: String, evaluateRequest: EvaluateRequest) =
        evaluateService.putEvaluates(thunderId, evaluateRequest)
}