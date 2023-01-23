package com.koreatech.thunder.domain.model

data class SelectableHashtag(
    val hashtag: Hashtag,
    val isSelected: Boolean = false
)

val dummySelectableHashtag = listOf(
    SelectableHashtag(
        Hashtag.SPORT
    ),
    SelectableHashtag(
        Hashtag.MOVIE
    ),
    SelectableHashtag(
        Hashtag.WALK
    )
)
