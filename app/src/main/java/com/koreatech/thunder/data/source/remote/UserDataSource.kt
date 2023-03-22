package com.koreatech.thunder.data.source.remote

import com.koreatech.thunder.data.model.request.AlarmStateRequest
import com.koreatech.thunder.data.model.request.ReportRequest
import com.koreatech.thunder.data.model.request.UserRequest
import com.koreatech.thunder.data.service.UserService
import com.koreatech.thunder.domain.model.Thunder
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userService: UserService
) {
    suspend fun getProfile() = userService.getProfile().toUser()
    suspend fun getThunderRecords(): List<Thunder> = userService.getThunderRecords()
    suspend fun getAlarmStates(): List<Boolean> = userService.getAlarmStates()
    suspend fun getUserHashtags(): List<String> = userService.getUserHashtags().hashtags
    suspend fun reportUser(body: ReportRequest) = userService.reportUser(body)
    suspend fun putUserProfile(body: UserRequest) = userService.putUserProfile(body)
    suspend fun patchAlarmState(body: AlarmStateRequest) = userService.patchAlarmState(body = body)
    suspend fun withdrawUser() = userService.withdrawUser()
}
