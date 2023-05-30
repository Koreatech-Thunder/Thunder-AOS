package com.koreatech.thunder.domain.repository

import com.koreatech.thunder.domain.model.*

interface UserRepository {
    suspend fun getProfile(): Result<User>
    suspend fun getThunderRecords(): Result<List<Thunder>>
    suspend fun getAlarmStates(): Result<List<Boolean>>
    suspend fun getUserHashtags(): Result<List<SelectableHashtag>>
    suspend fun reportUser(userId: String): Result<Unit>
    suspend fun putUserProfile(
        name: String,
        introduction: String,
        hashtags: List<Hashtag>,
        profile: ProfileType
    ): Result<Unit>

    suspend fun putAlarmState(alarmStates: List<Boolean>): Result<Unit>
    suspend fun withdrawUser(): Result<Unit>
}
