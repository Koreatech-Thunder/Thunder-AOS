package com.koreatech.thunder.feature.thunder.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderRowSpaceBetweenSlot
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.User

@Composable
fun ThunderParticipantsSection(
    participants: List<User>,
    limitParticipantsCount: Int,
    showBottomSheet: (User) -> Unit
) {
    Column(
        modifier = Modifier
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
                    participants.size,
                    limitParticipantsCount
                )
            }
        )
        BlankSpace(8.dp)
        Image(painter = painterResource(id = R.drawable.ic_school), contentDescription = "")
        ThunderParticipants(participants, 3, showBottomSheet)
    }
}
