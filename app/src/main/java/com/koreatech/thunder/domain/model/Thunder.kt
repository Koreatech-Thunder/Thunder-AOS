package com.koreatech.thunder.domain.model

data class Thunder(
    val thunderId: String,
    val title: String,
    val content: String,
    val deadline: String,
    val hashtags: List<Hashtag>,
    val host: User,
    val participants: List<User>,
    val limitParticipantsCnt: Int,
    val thunderState: ThunderState
)

val dummyThunders = listOf(
    Thunder(
        thunderId = "thunder1",
        title = "농구할 사람",
        content = "수요일에 농구 할 사람",
        deadline = "2023/02/18",
        hashtags = listOf(Hashtag.SPORT),
        participants = dummyUsers,
        host = dummyUsers[0],
        limitParticipantsCnt = 8,
        thunderState = ThunderState.NON_MEMBER
    ),
    Thunder(
        thunderId = "thunder2",
        title = "헬스 메이트 구해요",
        content = "내일 18시에 운동 같이 할 사람",
        deadline = "2023/02/18",
        hashtags = listOf(Hashtag.HEALTH),
        participants = dummyUsers,
        host = dummyUsers[1],
        limitParticipantsCnt = 3,
        thunderState = ThunderState.MEMBER
    ),
    Thunder(
        thunderId = "thunder3",
        title = "영화 보러 갈 사람",
        content = "금요일에 아바타2 보러 갈 사람",
        deadline = "2023/02/18",
        hashtags = listOf(Hashtag.MOVIE),
        participants = listOf(dummyUsers[2]),
        host = dummyUsers[2],
        limitParticipantsCnt = 4,
        thunderState = ThunderState.HOST
    )
)

enum class ThunderState {
    NON_MEMBER, MEMBER, HOST
}
