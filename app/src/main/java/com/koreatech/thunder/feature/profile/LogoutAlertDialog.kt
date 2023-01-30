package com.koreatech.thunder.feature.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.ThunderAlterDialogSlot

@Composable
fun LogoutAlertDialog() {
    ThunderAlterDialogSlot(
        titleText = stringResource(R.string.thunder_logout_title),
        contentText = stringResource(R.string.thunder_logout_content),
        confirmButtonText = stringResource(R.string.thunder_logout_confirm),
        dismissButtonText = stringResource(R.string.thunder_logout_cancel)
    )
}
