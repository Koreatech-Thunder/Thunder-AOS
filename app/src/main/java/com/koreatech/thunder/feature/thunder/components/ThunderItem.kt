package com.koreatech.thunder.feature.thunder.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.feature.thunder.model.ThunderUi
import com.koreatech.thunder.feature.thunder.model.previewThunderUis

@Composable
fun ThunderItem(
    thunderUi: ThunderUi
) {
    Column(
        modifier = Modifier.clip(RoundedCornerShape(8.dp))
    ) {
        ThunderDetailSection(thunderUi = thunderUi)
        ThunderParticipantsSection(thunderUi.participants, thunderUi.limitParticipantsCnt)
    }
}

@Preview(showBackground = true)
@Composable
private fun ThunderPreview() {
    ThunderTheme {
        ThunderItem(thunderUi = previewThunderUis[0])
    }
}
