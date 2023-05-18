package com.koreatech.thunder.domain.repository

import com.koreatech.thunder.domain.model.EvaluateThunder

interface EvaluateRepository {
    suspend fun getEvaluateUsers(thunderId: String): Result<EvaluateThunder>
}