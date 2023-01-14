package com.koreatech.thunder.feature.thunder.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.designsystem.components.ThunderHashtagText
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.feature.thunder.model.HashtagUi

@Composable
fun ThunderTextHashtags(hashtags: List<HashtagUi>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(hashtags) { hashtag ->
            ThunderHashtagText(
                textStyle = ThunderTheme.typography.h6,
                color = Orange,
                hashtagUi = hashtag
            )
        }
    }
}
