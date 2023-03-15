package com.koreatech.thunder.data.source.remote

import com.koreatech.thunder.data.model.ThunderResponse
import com.koreatech.thunder.data.model.request.ThunderRequest
import com.koreatech.thunder.data.service.ThunderService
import javax.inject.Inject

class ThunderDataSource @Inject constructor(
    private val thunderService: ThunderService
) {

    suspend fun getThunders(): List<ThunderResponse> = thunderService.getThunders()

    suspend fun getThundersWithHashtag(
        hashtag: String
    ): List<ThunderResponse> = thunderService.getThundersWithHashtag(hashtag)

    suspend fun getThunder(
        thunderId: String
    ): ThunderResponse = thunderService.getThunder(thunderId)

    suspend fun postThunder(
        body: ThunderRequest
    ) = thunderService.postThunder(body)

    suspend fun editThunder(
        thunderId: String,
        body: ThunderRequest
    ) = thunderService.editThunder(thunderId, body)

    suspend fun joinThunder(
        thunderId: String
    ) = thunderService.joinThunder(thunderId)

    suspend fun outThunder(
        thunderId: String
    ) = thunderService.outThunder(thunderId)
}
