package com.koreatech.thunder.data.repository

import com.koreatech.thunder.data.model.request.EvaluateRequest
import com.koreatech.thunder.data.model.request.EvaluateUserRequest
import com.koreatech.thunder.data.source.remote.EvaluateDataSource
import com.koreatech.thunder.domain.model.EvaluateThunder
import com.koreatech.thunder.domain.model.EvaluateUser
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
                    chatResponse.users.map { userResponse -> userResponse.toUser() })
            }

    override suspend fun putEvaluate(
        thunderId: String, evaluateUsers: List<EvaluateUser>
    ): Result<Unit> = runCatching {
        val evaluate = evaluateUsers.map { user ->
            EvaluateUserRequest(
                userId = user.userId, score = user.score
            )
        }
        evaluateDataSource.putEvaluate(thunderId, EvaluateRequest(evaluate))
    }

}