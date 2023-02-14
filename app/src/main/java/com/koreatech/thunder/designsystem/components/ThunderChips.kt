package com.koreatech.thunder.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.domain.model.SelectableHashtag

@Composable
fun ThunderChips(
    modifier: Modifier = Modifier,
    selectableHashtags: List<SelectableHashtag>,
    isClickable: Boolean = false,
    spanCount: Int = 4,
    selectHashtag: (Int) -> Unit = {}
) {
    val size = selectableHashtags.size
    val columnCount = if (size == 0) 0 else 1 + (size - 1) / spanCount
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
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
                            ThunderChip(
                                index = itemIndex,
                                selectableHashtag = selectableHashtags[itemIndex],
                                isClickable = isClickable,
                                selectHashtag = selectHashtag
                            )
                        }
                    } else {
                        Spacer(Modifier.weight(1F, fill = true))
                    }
                }
            }
        }
    }
}
