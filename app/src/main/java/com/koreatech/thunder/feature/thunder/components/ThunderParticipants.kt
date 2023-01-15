package com.koreatech.thunder.feature.thunder.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.feature.thunder.model.UserUi

@Composable
fun ThunderParticipants(
    participants: List<UserUi>,
    spanCount: Int
) {
    val size = participants.size
    val columnCount = if (size == 0) 0 else 1 + (size - 1) / spanCount
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(columnCount) { colIndex ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                repeat(spanCount) { rowIndex ->
                    val itemIndex = spanCount * colIndex + rowIndex
                    if (itemIndex < size) {
                        Box(
                            modifier = Modifier.weight(1f, fill = true),
                            propagateMinConstraints = true
                        ) {
                            ThunderUser(userUi = participants[itemIndex])
                        }
                    } else {
                        Spacer(Modifier.weight(1F, fill = true))
                    }
                }
            }
        }
    }
}
