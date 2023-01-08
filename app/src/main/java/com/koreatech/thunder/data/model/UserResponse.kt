package com.koreatech.thunder.data.model

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.User

data class UserResponse(
    val userId: String,
    val name: String,
    val introduction: String,
    val temperature: Int,
    val hashtags: List<String>
)

fun UserResponse.toUser(): User = User(
    userId = userId,
    name = name,
    introduction = introduction,
    temperature = temperature,
    hashtags = hashtags.map { hashtag -> Hashtag.valueOf(hashtag) }
)
