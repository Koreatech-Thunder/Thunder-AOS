package com.koreatech.thunder.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.feature.thunder.HashtagIndexState

@Composable
fun ThunderChips(
    hashtags: List<Hashtag>,
    hashtagIndexState: HashtagIndexState = HashtagIndexState.ACTIVE,
    selectHashtag: (Int) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemsIndexed(hashtags) { idx, hashtag ->
            ThunderChip(
                index = idx,
                hashtag = hashtag,
                hashtagIndexState = hashtagIndexState,
                selectHashtag
            )
        }
    }
}
