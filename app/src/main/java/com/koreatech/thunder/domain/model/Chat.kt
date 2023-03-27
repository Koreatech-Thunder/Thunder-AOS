package com.koreatech.thunder.domain.model

data class Chat(
    val id: String,
    val thunderId: String,
    val user: ChatUser,
    val message: String,
    val createdAt: String,
    val state: ChatState
)

val dummyChats = listOf(
    Chat(
        id = "chat1",
        thunderId = "thunder1",
        user = dummyChatUser[0],
        message = "공 누가 들고 가나요",
        createdAt = "15:00",
        state = ChatState.OTHER
    ),
    Chat(
        id = "chat2",
        thunderId = "thunder2",
        user = dummyChatUser[1],
        message = "오늘 어디 하나요",
        createdAt = "15:00",
        state = ChatState.OTHER
    ),
    Chat(
        id = "chat3",
        thunderId = "thunder3",
        user = dummyChatUser[2],
        message = "영화 뭐 보나요",
        createdAt = "15:00",
        state = ChatState.ME
    ),
    Chat(
        id = "chat4",
        thunderId = "thunder1",
        user = dummyChatUser[0],
        message = "공 누가 들고 가나요",
        createdAt = "15:00",
        state = ChatState.OTHER
    ),
    Chat(
        id = "chat5",
        thunderId = "thunder2",
        user = dummyChatUser[2],
        message = "오늘 어디 하나요",
        createdAt = "15:00",
        state = ChatState.ME
    ),
    Chat(
        id = "chat6",
        thunderId = "thunder3",
        user = dummyChatUser[2],
        message = "영화 뭐 보나요",
        createdAt = "15:00",
        state = ChatState.ME
    ),
    Chat(
        id = "chat1",
        thunderId = "thunder1",
        user = dummyChatUser[0],
        message = "공 누가 들고 가나요",
        createdAt = "15:00",
        state = ChatState.OTHER
    ),
    Chat(
        id = "chat2",
        thunderId = "thunder2",
        user = dummyChatUser[1],
        message = "오늘 어디 하나요",
        createdAt = "15:00",
        state = ChatState.OTHER
    ),
    Chat(
        id = "chat3",
        thunderId = "thunder3",
        user = dummyChatUser[2],
        message = "영화 뭐 보나요",
        createdAt = "15:00",
        state = ChatState.ME
    ),
    Chat(
        id = "chat4",
        thunderId = "thunder1",
        user = dummyChatUser[0],
        message = "공 누가 들고 가나요",
        createdAt = "15:00",
        state = ChatState.OTHER
    ),
    Chat(
        id = "chat5",
        thunderId = "thunder2",
        user = dummyChatUser[2],
        message = "오늘 어디 하나요",
        createdAt = "15:00",
        state = ChatState.ME
    ),
    Chat(
        id = "chat6",
        thunderId = "thunder3",
        user = dummyChatUser[2],
        message = "영화 뭐 보나요",
        createdAt = "15:00",
        state = ChatState.ME
    )
)
