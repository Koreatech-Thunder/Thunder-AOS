package com.koreatech.thunder.data.source.remote

import com.koreatech.thunder.data.model.ThunderResponse
import com.koreatech.thunder.data.model.UserResponse
import com.koreatech.thunder.data.model.request.ThunderRequest
import com.koreatech.thunder.data.service.ThunderApi
import javax.inject.Inject

class ThunderRemoteDataSource @Inject constructor(
    private val thunderApi: ThunderApi
) {
    suspend fun getThunders(): List<ThunderResponse> {
        TODO("Not yet implemented")
    }

    suspend fun getHashTaggedThunders(hashTag: String): List<ThunderResponse> {
        TODO("Not yet implemented")
    }

    suspend fun getUser(userId: String): UserResponse {
        TODO("Not yet implemented")
    }

    suspend fun postThunder(
        thunderRequest: ThunderRequest
    ) {
        TODO("Not yet implemented")
    }

    suspend fun enterThunder(userId: String) {
        TODO("Not yet implemented")
    }

    suspend fun cancelThunder(userId: String) {
        TODO("Not yet implemented")
    }
}
