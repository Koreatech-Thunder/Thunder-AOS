package com.koreatech.thunder.feature.thunder.model

import com.koreatech.thunder.domain.model.Hashtag

data class HashtagUi(
    val hashtag: Hashtag,
    val isSelected: Boolean = false
) {
    fun toEmpty() = Hashtag.values().map { hashtag -> HashtagUi(hashtag) }
}

val previewHashtagUis = listOf(
    HashtagUi(hashtag = Hashtag.SPORT),
    HashtagUi(hashtag = Hashtag.HEALTH, isSelected = true),
    HashtagUi(hashtag = Hashtag.MOVIE),
    HashtagUi(hashtag = Hashtag.WALK, isSelected = true),
    HashtagUi(hashtag = Hashtag.CALLVAN),
    HashtagUi(hashtag = Hashtag.EAT, isSelected = true),
    HashtagUi(hashtag = Hashtag.STUDY)
)
