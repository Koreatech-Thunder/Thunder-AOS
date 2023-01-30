package com.koreatech.thunder.feature.thunder.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.ThunderAlterDialogSlot

@Composable
fun ReportAlertDialog() {
    ThunderAlterDialogSlot(
        isSingle = true,
        titleText = stringResource(R.string.thunder_report_title),
        contentText = stringResource(R.string.thunder_report_content),
        confirmButtonText = stringResource(R.string.thunder_report_complete),
        onDismissRequest = { }
    )
}
