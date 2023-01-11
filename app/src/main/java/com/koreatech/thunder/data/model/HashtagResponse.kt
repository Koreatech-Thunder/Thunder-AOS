package com.koreatech.thunder.data.model

import com.koreatech.thunder.domain.model.Hashtag

data class HashtagResponse(
    val hashtags: List<String>
) {
    fun toHashtag() = hashtags.forEach { hashtag -> Hashtag.valueOf(hashtag) }
}
