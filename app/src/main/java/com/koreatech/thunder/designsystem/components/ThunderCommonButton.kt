package com.koreatech.thunder.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThunderCommonButton(
    modifier: Modifier,
    buttonText: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        buttonText()
    }
}
