package com.koreatech.thunder.domain.model

data class ChatRoom(
    val id: String,
    val title: String,
    val limitMemberCnt: Int,
    val joinMemberCnt: Int,
    val endTime: String,
    val lastChat: Chat?,
    val chatRoomState: ChatRoomState
)

enum class ChatRoomState {
    EVALUATE, RUNNING
}

val dummyChatRooms = listOf(
    ChatRoom(
        id = "thunder1",
        title = "농구할 사람",
        limitMemberCnt = 3,
        joinMemberCnt = 8,
        endTime = "2023/02/18",
        lastChat = dummyChats[0],
        chatRoomState = ChatRoomState.EVALUATE
    ),
    ChatRoom(
        id = "thunder2",
        title = "헬스 메이트 구해요",
        limitMemberCnt = 3,
        joinMemberCnt = 3,
        endTime = "2023/02/18",
        lastChat = dummyChats[1],
        chatRoomState = ChatRoomState.RUNNING
    ),
    ChatRoom(
        id = "thunder3",
        title = "영화 보러 갈 사람",
        limitMemberCnt = 4,
        joinMemberCnt = 1,
        endTime = "2023/02/18",
        lastChat = dummyChats[2],
        chatRoomState = ChatRoomState.RUNNING
    ),
    ChatRoom(
        id = "thunder4",
        title = "산책할 사람",
        limitMemberCnt = 2,
        joinMemberCnt = 1,
        endTime = "2023/02/18",
        lastChat = null,
        chatRoomState = ChatRoomState.RUNNING
    )
)
