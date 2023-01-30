package com.koreatech.thunder.feature.thunder.components

import androidx.compose.runtime.Composable
import com.koreatech.thunder.designsystem.components.ThunderAlterDialogSlot

@Composable
fun ReportAlertDialog() {
    ThunderAlterDialogSlot(
        isSingle = true,
        titleText = "신고완료",
        contentText = "해당 사용자를 신고하였습니다.\n신고하신 내용은 충분한 검토 후  결과를 알려드리도록 하겠습니다.",
        confirmButtonText = "완료",
        onDismissRequest = { }
    )
}
