package com.koreatech.thunder.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme

@Composable
fun ThunderAlterDialogSlot(
    titleText: String,
    contentText: String,
    confirmButtonText: String,
    dismissButtonText: String,
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = titleText,
                style = ThunderTheme.typography.h2
            )
        },
        text = {
            Text(
                text = contentText,
                style = ThunderTheme.typography.h5
            )
        },
        confirmButton = {
            Text(
                modifier = Modifier
                    .clickable {
                        onConfirmRequest()
                        onDismissRequest()
                    },
                text = confirmButtonText,
                color = Orange,
                style = ThunderTheme.typography.h5
            )
        },
        dismissButton = {
            Text(
                modifier = Modifier
                    .clickable { onDismissRequest() },
                text = dismissButtonText,
                color = Gray,
                style = ThunderTheme.typography.h5
            )
        },
        shape = RoundedCornerShape(8.dp)
    )
}
