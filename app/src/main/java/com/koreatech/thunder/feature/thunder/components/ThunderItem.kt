package com.koreatech.thunder.feature.thunder.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderRowSpaceBetweenSlot
import com.koreatech.thunder.designsystem.style.Gray200
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.feature.thunder.model.ThunderUi
import com.koreatech.thunder.feature.thunder.model.previewThunderUis

@Composable
fun ThunderItem(
    thunderUi: ThunderUi
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = Gray200)
            .padding(16.dp)
    ) {
        BlankSpace(10.dp)
        Text(
            text = thunderUi.deadline,
            style = MaterialTheme.typography.body2,
            color = Orange
        )
        BlankSpace(10.dp)
        ThunderTextHashtags(thunderUi.hashtags)
        BlankSpace(8.dp)
        Text(
            text = thunderUi.title,
            style = MaterialTheme.typography.h6
        )
        BlankSpace(10.dp)
        Text(
            text = thunderUi.content,
            style = MaterialTheme.typography.body2
        )
        BlankSpace(24.dp)
        ThunderRowSpaceBetweenSlot(
            prefixComponent = {
                ThunderUser(thunderUi.host)
            },
            postfixComponent = {
                ThunderButton(thunderUi.thunderState)
            }
        )
        BlankSpace(20.dp)
        ThunderRowSpaceBetweenSlot(
            prefixComponent = {
                Text(
                    text = stringResource(R.string.participants),
                    style = MaterialTheme.typography.body2
                )
            },
            postfixComponent = {
                ThunderParticipantsDetailSection(
                    thunderUi.participants.size,
                    thunderUi.limitParticipantsCnt
                )
            }
        )
        BlankSpace(8.dp)
        ThunderParticipants(thunderUi.participants)
        BlankSpace(16.dp)
    }
}

@Preview(showBackground = true)
@Composable
private fun ThunderPreview() {
    ThunderItem(thunderUi = previewThunderUis[0])
}
