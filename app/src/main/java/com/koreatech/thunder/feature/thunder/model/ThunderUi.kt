package com.koreatech.thunder.feature.thunder.model

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.Thunder

data class ThunderUi(
    val thunderId: String,
    val title: String,
    val content: String,
    val deadline: String,
    val hashtags: List<HashtagUi>,
    val host: UserUi,
    val participants: List<UserUi>,
    val limitParticipantsCnt: Int,
    val thunderState: ThunderState
) {
    constructor(thunder: Thunder, userId: String) : this(
        thunderId = thunder.thunderId,
        title = thunder.title,
        content = thunder.content,
        deadline = thunder.deadline,
        hashtags = thunder.hashtags.map { HashtagUi(it) },
        host = UserUi(thunder.host),
        participants = thunder.participants.map { member -> UserUi(member) },
        limitParticipantsCnt = thunder.limitParticipantsCnt,
        thunderState =
        if (thunder.host.userId == userId) ThunderState.HOST
        else if (thunder.participants.map { it.userId }.contains(userId)) ThunderState.MEMBER
        else ThunderState.NON_MEMBER
    )
}

val previewThunderUis = listOf(
    ThunderUi(
        thunderId = "thunder1",
        title = "농구할 사람",
        content = "수요일에 농구 할 사람",
        deadline = "2023/02/18",
        hashtags = listOf(HashtagUi(Hashtag.SPORT)),
        participants = previewUserUis,
        host = previewUserUis[0],
        limitParticipantsCnt = 8,
        thunderState = ThunderState.HOST
    ),
    ThunderUi(
        thunderId = "thunder2",
        title = "헬스 메이트 구해요",
        content = "내일 18시에 운동 같이 할 사람",
        deadline = "2023/02/18",
        hashtags = listOf(HashtagUi(Hashtag.HEALTH)),
        participants = previewUserUis,
        host = previewUserUis[1],
        limitParticipantsCnt = 3,
        thunderState = ThunderState.MEMBER
    ),
    ThunderUi(
        thunderId = "thunder3",
        title = "영화 보러 갈 사람",
        content = "금요일에 아바타2 보러 갈 사람",
        deadline = "2023/02/18",
        hashtags = listOf(HashtagUi(Hashtag.MOVIE)),
        participants = listOf(previewUserUis[2]),
        host = previewUserUis[2],
        limitParticipantsCnt = 4,
        thunderState = ThunderState.NON_MEMBER
    )
)

enum class ThunderState {
    NON_MEMBER, MEMBER, HOST
}
