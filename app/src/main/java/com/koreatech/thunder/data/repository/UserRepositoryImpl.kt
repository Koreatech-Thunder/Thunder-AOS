package com.koreatech.thunder.data.repository

import com.koreatech.thunder.data.model.request.AlarmStateRequest
import com.koreatech.thunder.data.model.request.ReportRequest
import com.koreatech.thunder.data.model.request.UserRequest
import com.koreatech.thunder.data.source.remote.UserDataSource
import com.koreatech.thunder.domain.model.*
import com.koreatech.thunder.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun getProfile(): Result<User> =
        runCatching { userDataSource.getProfile() }

    override suspend fun getThunderRecords(): Result<List<Thunder>> =
        runCatching { userDataSource.getThunderRecords() }

    override suspend fun getAlarmStates(): Result<List<Boolean>> =
        runCatching { userDataSource.getAlarmStates() }

    override suspend fun getUserHashtags(): Result<List<SelectableHashtag>> =
        runCatching {
            userDataSource.getUserHashtags()
                .map { hashtag -> SelectableHashtag(Hashtag.valueOf(hashtag)) }
        }

    override suspend fun reportUser(userId: String): Result<Unit> =
        runCatching { userDataSource.reportUser(ReportRequest(userId)) }

    override suspend fun putUserProfile(
        name: String,
        introduction: String,
        hashtags: List<Hashtag>,
        profile: ProfileType
    ): Result<Unit> =
        runCatching {
            userDataSource.putUserProfile(
                UserRequest(
                    name,
                    introduction,
                    hashtags.map { it.toString() },
                    profile.toString()
                )
            )
        }

    override suspend fun putAlarmState(alarmStates: List<Boolean>): Result<Unit> =
        runCatching { userDataSource.putAlarmState(AlarmStateRequest(alarmStates)) }

    override suspend fun withdrawUser(): Result<Unit> =
        runCatching { userDataSource.withdrawUser() }
}
