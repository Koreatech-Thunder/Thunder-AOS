package com.koreatech.thunder.data.source.remote.fake

import com.koreatech.thunder.data.model.ThunderResponse
import com.koreatech.thunder.data.model.UserResponse
import com.koreatech.thunder.data.model.request.ThunderRequest
import com.koreatech.thunder.data.source.remote.ThunderDataSource

class FakeThunderDataSourceImpl(
    private var thunders: MutableList<ThunderResponse>,
    private val users: MutableList<UserResponse>
) : ThunderDataSource {

    override suspend fun getThunders(): List<ThunderResponse> = thunders

    override suspend fun getHashTaggedThunders(hashTag: String): List<ThunderResponse> =
        thunders.filter { it.hashtags.contains(hashTag) }

    override suspend fun getUser(userId: String): UserResponse =
        users.first { it.userId == userId }

    override suspend fun postThunder(thunderRequest: ThunderRequest) {
        val user = users.first { it.userId == thunderRequest.userId }
        val thunderReq = ThunderResponse(
            thunderId = "new_thunder",
            title = thunderRequest.title,
            content = thunderRequest.content,
            hashtags = thunderRequest.hashtags,
            host = user,
            participants = listOf(user),
            limitParticipantsCnt = thunderRequest.limitParticipantsCnt,
            deadline = thunderRequest.deadline
        )
        thunders.add(thunderReq)
    }

    override suspend fun enterThunder(thunderId: String, userId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun cancelThunder(thunderId: String, userId: String) {
        TODO("Not yet implemented")
    }
}
