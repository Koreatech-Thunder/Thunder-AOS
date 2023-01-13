package com.koreatech.thunder.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ThunderRowSpaceBetweenSlot(
    modifier: Modifier = Modifier,
    prefixComponent: @Composable () -> Unit,
    postfixComponent: @Composable () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        prefixComponent()
        postfixComponent()
    }
}
