package com.koreatech.thunder.domain.repository

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.model.Thunder

interface ThunderRepository {
    suspend fun getThunders(): Result<List<Thunder>>
    suspend fun getThundersWithHashtag(hashTag: Hashtag): Result<List<Thunder>>
    suspend fun postThunder(
        title: String,
        content: String,
        hashtags: List<Hashtag>,
        limitParticipantsCnt: Int,
        deadline: String
    ): Result<Unit>

    suspend fun getHashtags(): Result<List<SelectableHashtag>>
    suspend fun getThunder(thunderId: String): Result<Thunder>
    suspend fun editThunder(
        thunderId: String,
        title: String,
        content: String,
        hashtags: List<Hashtag>,
        limitParticipantsCnt: Int,
        deadline: String
    ): Result<Unit>

    suspend fun joinThunder(thunderId: String): Result<Unit>
    suspend fun outThunder(thunderId: String): Result<Unit>
}
