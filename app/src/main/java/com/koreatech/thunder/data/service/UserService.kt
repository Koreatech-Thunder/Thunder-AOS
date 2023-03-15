package com.koreatech.thunder.data.service

import com.koreatech.thunder.data.model.request.AlarmStateRequest
import com.koreatech.thunder.data.model.request.ReportRequest
import com.koreatech.thunder.data.model.request.UserRequest
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.domain.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserService {
    @GET("/user/profile")
    suspend fun getProfile(): User

    @GET("/user/participate")
    suspend fun getThunderRecords(): List<Thunder>

    @GET("/user/alarm")
    suspend fun getAlarmStates(): List<Boolean>

    @GET("/user/hashtags")
    suspend fun getUserHashtags(): List<String>

    @POST("/user/report")
    suspend fun reportUser(
        @Body body: ReportRequest
    )

    @PUT("/user")
    suspend fun putUserProfile(
        @Body body: UserRequest
    )

    @PATCH("/user/alarm")
    suspend fun patchAlarmState(
        @Body body: AlarmStateRequest
    )

    @DELETE("/user")
    suspend fun withdrawUser()
}
