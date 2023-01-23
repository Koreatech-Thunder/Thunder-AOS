package com.koreatech.thunder.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.domain.model.SelectableHashtag

@Composable
fun ThunderChips(
    selectableHashtags: List<SelectableHashtag>,
    isClickable: Boolean = false,
    selectHashtag: (Int) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemsIndexed(selectableHashtags) { idx, hashtag ->
            ThunderChip(
                index = idx,
                selectableHashtag = hashtag,
                isClickable = isClickable,
                selectHashtag = selectHashtag
            )
        }
    }
}
