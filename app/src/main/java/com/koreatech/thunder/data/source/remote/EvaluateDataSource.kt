package com.koreatech.thunder.data.source.remote

import com.koreatech.thunder.data.service.EvaluateService
import javax.inject.Inject

class EvaluateDataSource @Inject constructor(
    private val evaluateService: EvaluateService
) {

    suspend fun getEvaluateUsers(thunderId: String) =
        evaluateService.getEvaluateUsers(thunderId)
}