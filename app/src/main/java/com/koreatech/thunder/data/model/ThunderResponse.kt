package com.koreatech.thunder.data.model

import com.google.gson.annotations.SerializedName
import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.domain.model.ThunderState

data class ThunderResponse(
    @SerializedName("_id") val thunderId: String,
    val title: String,
    val content: String,
    val deadline: String,
    val hashtags: List<String>,
    val members: List<UserResponse>,
    @SerializedName("limitMembersCnt") val limitParticipantsCnt: Int,
    val thunderState: String
)

fun ThunderResponse.toThunder(): Thunder = Thunder(
    thunderId = thunderId,
    title = title,
    content = content,
    deadline = deadline,
    hashtags = hashtags.map { hashtag -> Hashtag.valueOf(hashtag) },
    participants = members.map { participant -> participant.toUser() },
    limitParticipantsCnt = limitParticipantsCnt,
    thunderState = ThunderState.valueOf(thunderState)
)

val dummyThunderResponses = listOf(
    ThunderResponse(
        thunderId = "thunder1",
        title = "농구할 사람",
        content = "수요일에 농구 할 사람",
        deadline = "2023/02/18",
        hashtags = listOf("SPORT"),
        members = dummyUserResponses,
        limitParticipantsCnt = 8,
        thunderState = ThunderState.NON_MEMBER.name
    ),
    ThunderResponse(
        thunderId = "thunder2",
        title = "헬스 메이트 구해요",
        content = "내일 18시에 운동 같이 할 사람",
        deadline = "2023/02/18",
        hashtags = listOf("HEALTH"),
        members = dummyUserResponses,
        limitParticipantsCnt = 3,
        thunderState = ThunderState.MEMBER.name
    ),
    ThunderResponse(
        thunderId = "thunder3",
        title = "영화 보러 갈 사람",
        content = "금요일에 아바타2 보러 갈 사람",
        deadline = "2023/02/18",
        hashtags = listOf("MOVIE"),
        members = listOf(dummyUserResponses[2]),
        limitParticipantsCnt = 4,
        thunderState = ThunderState.HOST.name
    )
)
