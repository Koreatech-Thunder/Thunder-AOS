package com.koreatech.thunder.feature.thunder.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderRowSpaceBetweenSlot
import com.koreatech.thunder.designsystem.style.Gray200
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.Orange200
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.feature.thunder.model.ThunderUi
import com.koreatech.thunder.feature.thunder.model.previewThunderUis

@Composable
fun ThunderItem(
    thunderUi: ThunderUi
) {
    Column {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .background(color = Gray200)
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Text(
                text = thunderUi.deadline,
                style = ThunderTheme.typography.h5,
                color = Orange
            )
            BlankSpace(10.dp)
            ThunderTextHashtags(thunderUi.hashtags)
            BlankSpace(8.dp)
            Text(
                text = thunderUi.title,
                style = ThunderTheme.typography.h4
            )
            BlankSpace(10.dp)
            Text(
                text = thunderUi.content,
                style = ThunderTheme.typography.h5
            )
            BlankSpace(12.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                ThunderButton(thunderUi.thunderState)
            }
        }
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                .background(color = Orange200)
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            ThunderRowSpaceBetweenSlot(
                prefixComponent = {
                    Text(
                        text = stringResource(R.string.participants),
                        style = ThunderTheme.typography.h5
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
            Image(painter = painterResource(id = R.drawable.ic_school), contentDescription = "")
            ThunderParticipants(thunderUi.participants)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ThunderPreview() {
    ThunderTheme {
        ThunderItem(thunderUi = previewThunderUis[0])
    }
}
