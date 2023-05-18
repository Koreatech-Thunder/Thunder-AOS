package com.koreatech.thunder.data.repository

import com.koreatech.thunder.data.source.remote.EvaluateDataSource
import com.koreatech.thunder.domain.model.EvaluateThunder
import com.koreatech.thunder.domain.repository.EvaluateRepository
import javax.inject.Inject

class EvaluateRepositoryImpl @Inject constructor(
    private val evaluateDataSource: EvaluateDataSource
) : EvaluateRepository {

    override suspend fun getEvaluateUsers(thunderId: String): Result<EvaluateThunder> =
        runCatching { evaluateDataSource.getEvaluateUsers(thunderId) }
            .mapCatching { chatResponse ->
                EvaluateThunder(
                    chatResponse.title,
                    chatResponse.users.map { userResponse -> userResponse.toUser() }
                )
            }
}