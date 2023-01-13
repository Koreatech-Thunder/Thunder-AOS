package com.koreatech.thunder.feature.thunder.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.feature.thunder.model.UserUi

@Composable
fun ThunderParticipants(participants: List<UserUi>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(participants) { user ->
            ThunderUser(userUi = user)
        }
    }
}