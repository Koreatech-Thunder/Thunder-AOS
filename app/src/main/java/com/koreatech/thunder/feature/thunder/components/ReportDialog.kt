package com.koreatech.thunder.feature.thunder.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.ThunderDialogSlot
import com.koreatech.thunder.designsystem.style.ThunderTheme

@Composable
fun ReportDialog(
    reportUser: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    ThunderDialogSlot(
        radius = 15.dp,
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 24.dp
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(R.string.thunder_report_dialog_title),
                style = ThunderTheme.typography.h2
            )
            Text(
                text = stringResource(R.string.thunder_report_dialog_content),
                style = ThunderTheme.typography.h5
            )
            Text(
                modifier = Modifier.clickable {
                    reportUser(0)
                    onDismissRequest()
                },
                text = stringResource(R.string.thunder_report_1),
                style = ThunderTheme.typography.b3
            )
            Text(
                modifier = Modifier.clickable {
                    reportUser(1)
                    onDismissRequest()
                },
                text = stringResource(R.string.thunder_report_2),
                style = ThunderTheme.typography.b3
            )
            Text(
                modifier = Modifier.clickable {
                    reportUser(2)
                    onDismissRequest()
                },
                text = stringResource(R.string.thunder_report_3),
                style = ThunderTheme.typography.b3
            )
            Text(
                modifier = Modifier.clickable {
                    reportUser(3)
                    onDismissRequest()
                },
                text = stringResource(R.string.thunder_report_4),
                style = ThunderTheme.typography.b3
            )
            Text(
                modifier = Modifier.clickable {
                    reportUser(4)
                    onDismissRequest()
                },
                text = stringResource(R.string.thunder_report_5),
                style = ThunderTheme.typography.b3
            )
        }
    }
}
