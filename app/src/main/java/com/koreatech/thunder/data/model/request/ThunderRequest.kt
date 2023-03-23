package com.koreatech.thunder.data.model.request

data class ThunderRequest(
    val title: String,
    val content: String,
    val hashtags: List<String>,
    val limitMembersCnt: Int,
    val deadline: String
)
