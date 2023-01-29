package com.koreatech.thunder.feature.thunder_add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun ThunderCreateDialog(onDismissRequest: () -> Unit) {
    ThunderDialogSlot(
        radius = 20.dp,
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier.padding(
                vertical = 26.dp,
                horizontal = 28.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.thunder_create_title),
                style = ThunderTheme.typography.h2
            )
            BlankSpace(size = 32.dp)
            Text(
                text = stringResource(R.string.thunder_create_subtitle),
                style = ThunderTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            BlankSpace(size = 24.dp)
            Text(
                text = stringResource(R.string.thunder_create_content),
                style = ThunderTheme.typography.b3,
                textAlign = TextAlign.Center
            )
            BlankSpace(size = 54.dp)
            Text(
                modifier = Modifier
                    .noRippleClickable { onDismissRequest() },
                color = Orange,
                text = stringResource(R.string.thunder_confirm),
                style = ThunderTheme.typography.h5
            )
        }
    }
}
