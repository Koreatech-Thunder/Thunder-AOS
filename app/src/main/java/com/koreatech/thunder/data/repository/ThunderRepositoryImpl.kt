package com.koreatech.thunder.data.repository

import com.koreatech.thunder.data.model.request.ThunderRequest
import com.koreatech.thunder.data.model.toThunder
import com.koreatech.thunder.data.source.remote.ThunderDataSource
import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.domain.repository.ThunderRepository
import javax.inject.Inject

class ThunderRepositoryImpl @Inject constructor(
    private val thunderDataSource: ThunderDataSource
) : ThunderRepository {
    override suspend fun getThunders(): Result<List<Thunder>> =
        runCatching { thunderDataSource.getThunders().map { thunder -> thunder.toThunder() } }

    override suspend fun getThundersWithHashtag(hashTag: Hashtag): Result<List<Thunder>> =
        runCatching {
            thunderDataSource.getThundersWithHashtag(hashTag.toString())
                .map { thunder -> thunder.toThunder() }
        }

    override suspend fun postThunder(
        title: String,
        content: String,
        hashtags: List<Hashtag>,
        limitParticipantsCnt: Int,
        deadline: String
    ): Result<Unit> = runCatching {
        thunderDataSource.postThunder(
            ThunderRequest(
                title = title,
                content = content,
                hashtags = hashtags.map { hashtag -> hashtag.toString() },
                limitMembersCnt = limitParticipantsCnt,
                deadline = deadline
            )
        )
    }

    override suspend fun getHashtags(): Result<List<SelectableHashtag>> {
        TODO("Not yet implemented")
    }

    override suspend fun getThunder(thunderId: String): Result<Thunder> =
        runCatching { thunderDataSource.getThunder(thunderId).toThunder() }

    override suspend fun editThunder(
        thunderId: String,
        title: String,
        content: String,
        hashtags: List<Hashtag>,
        limitParticipantsCnt: Int,
        deadline: String
    ) = runCatching {
        thunderDataSource.editThunder(
            thunderId = thunderId,
            body = ThunderRequest(
                title = title,
                content = content,
                hashtags = hashtags.map { hashtag -> hashtag.toString() },
                limitMembersCnt = limitParticipantsCnt,
                deadline = deadline
            )
        )
    }

    override suspend fun joinThunder(thunderId: String) =
        runCatching { thunderDataSource.joinThunder(thunderId = thunderId) }

    override suspend fun outThunder(thunderId: String) =
        runCatching { thunderDataSource.outThunder(thunderId = thunderId) }
}
