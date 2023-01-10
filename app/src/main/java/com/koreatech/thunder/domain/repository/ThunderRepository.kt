package com.koreatech.thunder.domain.repository

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.domain.model.User

interface ThunderRepository {
    suspend fun getThunders(): Result<List<Thunder>>
    suspend fun getHashTaggedThunders(hashTag: Hashtag): Result<List<Thunder>>
    suspend fun getUser(userId: String): Result<User>
    suspend fun postThunder(
        userId: String,
        title: String,
        content: String,
        hashtags: List<Hashtag>,
        limitParticipantsCnt: Int,
        deadline: String
    ): Result<Unit>

    suspend fun enterThunder(thunderId: String, userId: String): Result<Unit>
    suspend fun cancelThunder(thunderId: String, userId: String): Result<Unit>
}
