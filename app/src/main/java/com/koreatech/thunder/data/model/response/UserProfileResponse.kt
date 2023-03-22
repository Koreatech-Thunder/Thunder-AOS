package com.koreatech.thunder.data.model.response

import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.model.User

data class UserProfileResponse(
    val name: String,
    val introduction: String,
    val hashtags: List<String>,
    val mannerTemperature: Double
) {
    fun toUser(): User = User(
        userId = "",
        name = name,
        introduction = introduction,
        hashtags = hashtags.map { SelectableHashtag(Hashtag.valueOf(it)) },
        temperature = mannerTemperature.toInt()
    )
}
