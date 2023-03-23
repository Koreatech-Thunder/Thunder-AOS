package com.koreatech.thunder.domain.model

data class User(
    val userId: String,
    val name: String,
    val introduction: String,
    val temperature: Int,
    val hashtags: List<SelectableHashtag>
)

val dummyUsers = listOf(
    User(
        userId = "KWY",
        name = "KWY",
        introduction = "컴퓨터 공학부",
        temperature = 36,
        hashtags = listOf(
            SelectableHashtag(Hashtag.SPORT, true),
            SelectableHashtag(Hashtag.HEALTH, true)
        )
    ),
    User(
        userId = "HSE",
        name = "HSE",
        introduction = "컴퓨터공학부",
        temperature = 36,
        hashtags = listOf(
            SelectableHashtag(Hashtag.MOVIE, true),
            SelectableHashtag(Hashtag.WALK, true)
        )
    ),
    User(
        userId = "MSB",
        name = "MSB",
        introduction = "컴퓨터공학부",
        temperature = 36,
        hashtags = listOf(
            SelectableHashtag(Hashtag.CALLVAN, true),
            SelectableHashtag(Hashtag.EAT, true)
        )
    )
)
