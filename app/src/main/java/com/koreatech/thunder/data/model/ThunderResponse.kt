package com.koreatech.thunder.data.model

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.Thunder

data class ThunderResponse(
    val thunderId: String,
    val title: String,
    val content: String,
    val deadline: String,
    val hashtags: List<String>,
    val host: UserResponse,
    val participants: List<UserResponse>,
    val limitParticipantsCnt: Int
)

fun ThunderResponse.toThunder(): Thunder = Thunder(
    thunderId = thunderId,
    title = title,
    content = content,
    deadline = deadline,
    hashtags = hashtags.map { hashtag -> Hashtag.valueOf(hashtag) },
    host = host.toUser(),
    participants = participants.map { participant -> participant.toUser() },
    limitParticipantsCnt = limitParticipantsCnt
)

val dummyThunderResponses = listOf(
    ThunderResponse(
        thunderId = "thunder1",
        title = "농구할 사람",
        content = "수요일에 농구 할 사람",
        deadline = "2023/02/18",
        hashtags = listOf("SPORT"),
        participants = dummyUserResponses,
        host = dummyUserResponses[0],
        limitParticipantsCnt = 8
    ),
    ThunderResponse(
        thunderId = "thunder2",
        title = "헬스 메이트 구해요",
        content = "내일 18시에 운동 같이 할 사람",
        deadline = "2023/02/18",
        hashtags = listOf("HEALTH"),
        participants = dummyUserResponses,
        host = dummyUserResponses[1],
        limitParticipantsCnt = 3
    ),
    ThunderResponse(
        thunderId = "thunder3",
        title = "영화 보러 갈 사람",
        content = "금요일에 아바타2 보러 갈 사람",
        deadline = "2023/02/18",
        hashtags = listOf("MOVIE"),
        participants = listOf(dummyUserResponses[2]),
        host = dummyUserResponses[2],
        limitParticipantsCnt = 4
    )
)
