package com.koreatech.thunder.domain.model

data class User(
    val userId: String,
    val name: String,
    val introduction: String,
    val temperature: Int,
    val hashtags: List<Hashtag>
)

val dummyUsers = listOf(
    User(
        userId = "KWY",
        name = "KWY",
        introduction = "컴퓨터 공학부",
        temperature = 36,
        hashtags = listOf(Hashtag.SPORT, Hashtag.HEALTH)
    ),
    User(
        userId = "HSE",
        name = "HSE",
        introduction = "컴퓨터공학부",
        temperature = 36,
        hashtags = listOf(Hashtag.MOVIE, Hashtag.WALK)
    ),
    User(
        userId = "MSB",
        name = "MSB",
        introduction = "컴퓨터공학부",
        temperature = 36,
        hashtags = listOf(Hashtag.CALLVAN, Hashtag.EAT)
    )
)
