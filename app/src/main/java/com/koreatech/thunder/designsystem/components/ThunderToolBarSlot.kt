package com.koreatech.thunder.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThunderToolBarSlot(
    modifier: Modifier,
    navigationIcon: @Composable () -> Unit = {},
    title: @Composable () -> Unit = {},
    action: @Composable () -> Unit = {}
) {
    ThunderRowSpaceBetweenSlot(
        modifier = modifier,
        prefixComponent = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                navigationIcon()
                title()
            }
        },
        postfixComponent = {
            action()
        }
    )
}
