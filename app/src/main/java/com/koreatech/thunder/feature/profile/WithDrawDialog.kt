package com.koreatech.thunder.feature.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderDialogSlot
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.feature.thunder.components.noRippleClickable

@Composable
fun WithDrawDialog(
    userName: String,
    confirmRequest: () -> Unit,
    onDismissRequest: () -> Unit
) {
    ThunderDialogSlot(
        radius = 20.dp,
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "$userName 님,\n잠시만요!",
                style = ThunderTheme.typography.h2
            )
            BlankSpace(size = 12.dp)
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.withdraw_title),
                style = ThunderTheme.typography.h2,
                textAlign = TextAlign.Center
            )
            BlankSpace(size = 36.dp)
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = stringResource(R.string.withdraw_content),
                style = ThunderTheme.typography.b3
            )
            BlankSpace(size = 36.dp)
            Text(
                modifier = Modifier
                    .noRippleClickable {
                        confirmRequest()
                        onDismissRequest()
                    }
                    .fillMaxWidth(),
                color = Orange,
                text = stringResource(R.string.withdraw_btn),
                style = ThunderTheme.typography.h5,
                textAlign = TextAlign.Center
            )
        }
    }
}
