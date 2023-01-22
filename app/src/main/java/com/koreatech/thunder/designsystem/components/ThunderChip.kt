package com.koreatech.thunder.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.Orange200
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.feature.thunder.HashtagIndexState

@Composable
fun ThunderChip(
    index: Int,
    hashtag: Hashtag,
    hashtagIndexState: HashtagIndexState,
    selectHashtag: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .clickable { selectHashtag(index) }
            .background(
                when (hashtagIndexState) {
                    HashtagIndexState.IDLE -> Orange200
                    HashtagIndexState.ACTIVE -> Orange
                    is HashtagIndexState.SELECTED -> if (hashtagIndexState.index == index) Orange else Orange200
                }
            )
            .padding(vertical = 8.dp, horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        ThunderHashtagText(
            textStyle = ThunderTheme.typography.h6,
            hashtag = hashtag,
            color = Color.White
        )
    }
}
