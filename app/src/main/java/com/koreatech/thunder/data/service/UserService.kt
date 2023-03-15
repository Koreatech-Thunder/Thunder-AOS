package com.koreatech.thunder.data.service

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserService {
    @GET("/user/profile")
    suspend fun getProfile()

    @GET("/user/participate")
    suspend fun getThunderRecords()

    @GET("/user/alarm")
    suspend fun getAlarmStates()

    @GET("/user/hashtags")
    suspend fun getUserHashtags()

    @POST("/user/report")
    suspend fun reportUser()

    @PUT("/user")
    suspend fun putUserProfile()

    @PATCH("/user/alarm")
    suspend fun patchAlarmState()

    @DELETE("/user")
    suspend fun withdrawUser()
}
