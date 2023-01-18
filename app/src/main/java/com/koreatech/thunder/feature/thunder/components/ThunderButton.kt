package com.koreatech.thunder.feature.thunder.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.ThunderCommonButton
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.ThunderState

@Composable
fun ThunderButton(thunderState: ThunderState) {
    ThunderCommonButton(
        modifier = Modifier
            .clickable {
                when (thunderState) {
                    ThunderState.NON_MEMBER -> TODO()
                    ThunderState.MEMBER -> TODO()
                    ThunderState.HOST -> TODO()
                }
            }
            .clip(RoundedCornerShape(8.dp))
            .background(Orange)
            .padding(vertical = 8.dp, horizontal = 18.dp),
        textStyle = ThunderTheme.typography.h6,
        text = when (thunderState) {
            ThunderState.NON_MEMBER -> stringResource(R.string.enter_thunder)
            ThunderState.MEMBER -> stringResource(R.string.cancel_thunder)
            ThunderState.HOST -> stringResource(R.string.edit_thunder)
        }
    )
}
