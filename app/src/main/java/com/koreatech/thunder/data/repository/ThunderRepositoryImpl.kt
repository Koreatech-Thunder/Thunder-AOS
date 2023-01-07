package com.koreatech.thunder.data.repository

import com.koreatech.thunder.data.source.remote.ThunderRemoteDataSource
import com.koreatech.thunder.domain.model.HashTag
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.domain.model.User
import com.koreatech.thunder.domain.repository.ThunderRepository
import javax.inject.Inject

class ThunderRepositoryImpl @Inject constructor(
    private val thunderRemoteDataSource: ThunderRemoteDataSource
) : ThunderRepository {
    override suspend fun getThunders(): Result<List<Thunder>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHashTaggedThunders(hashTag: HashTag): Result<List<Thunder>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(userId: String): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun postThunder(
        userId: String,
        title: String,
        content: String,
        hashtags: List<HashTag>,
        limitParticipantsCnt: Int,
        deadline: String
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun enterThunder(userId: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun cancelThunder(userId: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}
