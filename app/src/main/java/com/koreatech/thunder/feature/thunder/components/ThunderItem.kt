package com.koreatech.thunder.feature.thunder.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.designsystem.style.Orange200
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.domain.model.dummyThunders

@Composable
fun ThunderItem(
    thunder: Thunder
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = Orange200)
    ) {
        ThunderDetailSection(thunder = thunder)
        ThunderParticipantsSection(thunder.participants, thunder.limitParticipantsCnt)
    }
}

@Preview(showBackground = true)
@Composable
private fun ThunderPreview() {
    ThunderTheme {
        ThunderItem(thunder = dummyThunders[0])
    }
}
