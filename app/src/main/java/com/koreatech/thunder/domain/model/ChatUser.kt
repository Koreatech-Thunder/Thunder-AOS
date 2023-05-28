package com.koreatech.thunder.domain.model

data class ChatUser(
    val userId: String,
    val name: String,
    val profile: ProfileType = ProfileType.THUNDER,
)

val dummyChatUser = listOf(
    ChatUser(
        userId = dummyUsers[0].userId,
        name = dummyUsers[0].name
    ),
    ChatUser(
        userId = dummyUsers[1].userId,
        name = dummyUsers[1].name
    ),
    ChatUser(
        userId = dummyUsers[2].userId,
        name = dummyUsers[2].name
    )
)
