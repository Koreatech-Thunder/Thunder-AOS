package com.koreatech.thunder.feature.thunder.model

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.User

data class UserUi(
    val userId: String,
    val name: String,
    val introduction: String,
    val temperature: Int,
    val hashtags: List<Hashtag>
) {
    constructor(user: User) : this(
        userId = user.userId,
        name = user.name,
        introduction = user.introduction,
        temperature = user.temperature,
        hashtags = user.hashtags
    )
}

val previewUserUis = listOf(
    UserUi(
        userId = "KWY",
        name = "KWY",
        introduction = "컴퓨터 공학부",
        temperature = 36,
        hashtags = listOf(Hashtag.SPORT, Hashtag.HEALTH)
    ),
    UserUi(
        userId = "HSE",
        name = "HSE",
        introduction = "컴퓨터공학부",
        temperature = 36,
        hashtags = listOf(Hashtag.MOVIE, Hashtag.WALK)
    ),
    UserUi(
        userId = "MSB",
        name = "MSB",
        introduction = "컴퓨터공학부",
        temperature = 36,
        hashtags = listOf(Hashtag.CALLVAN, Hashtag.EAT)
    ),
    UserUi(
        userId = "KWY",
        name = "KWY",
        introduction = "컴퓨터 공학부",
        temperature = 36,
        hashtags = listOf(Hashtag.SPORT, Hashtag.HEALTH)
    ),
    UserUi(
        userId = "HSE",
        name = "HSE",
        introduction = "컴퓨터공학부",
        temperature = 36,
        hashtags = listOf(Hashtag.MOVIE, Hashtag.WALK)
    ),
    UserUi(
        userId = "MSB",
        name = "MSB",
        introduction = "컴퓨터공학부",
        temperature = 36,
        hashtags = listOf(Hashtag.CALLVAN, Hashtag.EAT)
    )
)
