package com.koreatech.thunder.data.repository

import com.koreatech.thunder.data.source.remote.ThunderDataSource
import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.domain.model.User
import com.koreatech.thunder.domain.repository.ThunderRepository
import javax.inject.Inject

class ThunderRepositoryImpl @Inject constructor(
    private val thunderDataSource: ThunderDataSource
) : ThunderRepository {
    override suspend fun getThunders(): Result<List<Thunder>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHashTaggedThunders(hashTag: Hashtag): Result<List<Thunder>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(userId: String): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun postThunder(
        userId: String,
        title: String,
        content: String,
        hashtags: List<Hashtag>,
        limitParticipantsCnt: Int,
        deadline: String
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getHashtags(): Result<List<Hashtag>> {
        TODO("Not yet implemented")
    }

    override suspend fun enterThunder(thunderId: String, userId: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun cancelThunder(thunderId: String, userId: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}
