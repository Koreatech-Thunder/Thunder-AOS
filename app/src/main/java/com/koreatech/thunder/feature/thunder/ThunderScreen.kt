package com.koreatech.thunder.feature.thunder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderChips
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.feature.thunder.components.ThunderItem
import com.koreatech.thunder.feature.thunder.model.previewHashtagUis
import com.koreatech.thunder.feature.thunder.model.previewThunderUis

@Preview(showBackground = true)
@Composable
private fun ThunderScreenPreview() {
    ThunderTheme {
        ThunderScreen(navController = rememberNavController())
    }
}

@Composable
fun ThunderScreen(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        ThunderToolBarSlot(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            title = {
                Text(
                    text = stringResource(R.string.thunder_title),
                    style = ThunderTheme.typography.h1
                )
            },
            action = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notifications),
                    contentDescription = ""
                )
            }
        )
        Text(
            text = stringResource(R.string.entering_thunder),
            style = ThunderTheme.typography.h3
        )
        BlankSpace(size = 16.dp)
        ThunderChips(previewHashtagUis)
        BlankSpace(size = 12.dp)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(previewThunderUis) { thunder ->
                ThunderItem(thunderUi = thunder)
            }
        }
    }
}
