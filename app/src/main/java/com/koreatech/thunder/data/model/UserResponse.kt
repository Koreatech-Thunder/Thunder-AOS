package com.koreatech.thunder.data.model

import com.google.gson.annotations.SerializedName
import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.model.User

data class UserResponse(
    val userId: String,
    val name: String,
    val introduction: String,
    @SerializedName("mannerTemperature") val temperature: Int,
    val hashtags: List<String>
)

fun UserResponse.toUser(): User = User(
    userId = userId,
    name = name,
    introduction = introduction,
    temperature = temperature,
    hashtags = hashtags.map { hashtag -> SelectableHashtag(Hashtag.valueOf(hashtag), true) }
)

val dummyUserResponses = listOf(
    UserResponse(
        userId = "KWY",
        name = "KWY",
        introduction = "컴퓨터 공학부",
        temperature = 36,
        hashtags = listOf("SPORT", "HEALTH")
    ),
    UserResponse(
        userId = "HSE",
        name = "HSE",
        introduction = "컴퓨터공학부",
        temperature = 36,
        hashtags = listOf("MOVIE", "WALK")
    ),
    UserResponse(
        userId = "MSB",
        name = "MSB",
        introduction = "컴퓨터공학부",
        temperature = 36,
        hashtags = listOf("CALLVAN", "EAT")
    )
)
