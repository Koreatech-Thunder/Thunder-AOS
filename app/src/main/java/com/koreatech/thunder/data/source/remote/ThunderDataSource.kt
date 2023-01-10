package com.koreatech.thunder.data.source.remote

import com.koreatech.thunder.data.model.ThunderResponse
import com.koreatech.thunder.data.model.UserResponse
import com.koreatech.thunder.data.model.request.ThunderRequest

interface ThunderDataSource {
    suspend fun getThunders(): List<ThunderResponse>
    suspend fun getHashTaggedThunders(hashTag: String): List<ThunderResponse>
    suspend fun getUser(userId: String): UserResponse
    suspend fun postThunder(thunderRequest: ThunderRequest)
    suspend fun enterThunder(thunderId: String, userId: String)
    suspend fun cancelThunder(thunderId: String, userId: String)
}
