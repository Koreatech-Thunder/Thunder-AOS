package com.koreatech.thunder.feature.thunder.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.style.Gray200
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.Thunder

@Composable
fun ThunderDetailSection(thunder: Thunder) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Gray200)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = thunder.deadline,
            style = ThunderTheme.typography.h5,
            color = Orange
        )
        BlankSpace(10.dp)
        ThunderTextHashtags(thunder.hashtags)
        BlankSpace(8.dp)
        Text(
            text = thunder.title,
            style = ThunderTheme.typography.h4
        )
        BlankSpace(10.dp)
        Text(
            text = thunder.content,
            style = ThunderTheme.typography.h5
        )
        BlankSpace(12.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            ThunderButton(thunder.thunderState)
        }
    }
}
