package com.koreatech.thunder.data.model.request

data class ThunderRequest(
    val userId: String,
    val title: String,
    val content: String,
    val hashtags: List<String>,
    val limitParticipantsCnt: Int,
    val deadline: String
)
