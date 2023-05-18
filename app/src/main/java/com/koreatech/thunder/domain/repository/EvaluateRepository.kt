package com.koreatech.thunder.domain.repository

import com.koreatech.thunder.domain.model.EvaluateThunder
import com.koreatech.thunder.domain.model.EvaluateUser

interface EvaluateRepository {
    suspend fun getEvaluateUsers(thunderId: String): Result<EvaluateThunder>
    suspend fun putEvaluate(thunderId: String, evaluateUsers: List<EvaluateUser>): Result<Unit>
}