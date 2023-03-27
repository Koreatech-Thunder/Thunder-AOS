package com.koreatech.thunder.data.service

import com.koreatech.thunder.data.model.request.AlarmStateRequest
import com.koreatech.thunder.data.model.request.ReportRequest
import com.koreatech.thunder.data.model.request.UserRequest
import com.koreatech.thunder.data.model.response.AlarmStateResponse
import com.koreatech.thunder.data.model.response.HashtagsResponse
import com.koreatech.thunder.data.model.response.UserProfileResponse
import com.koreatech.thunder.domain.model.Thunder
import retrofit2.http.*

interface UserService {
    @GET("/user/profile")
    suspend fun getProfile(): UserProfileResponse

    @GET("/user/record")
    suspend fun getThunderRecords(): List<Thunder>

    @GET("/user/alarm")
    suspend fun getAlarmStates(): AlarmStateResponse

    @GET("/user/hashtags")
    suspend fun getUserHashtags(): HashtagsResponse

    @POST("/user/report")
    suspend fun reportUser(
        @Body body: ReportRequest
    )

    @PUT("/user")
    suspend fun putUserProfile(
        @Body body: UserRequest
    )

    @PUT("/user")
    suspend fun putAlarmState(
        @Body body: AlarmStateRequest
    )

    @DELETE("/user")
    suspend fun withdrawUser()
}
