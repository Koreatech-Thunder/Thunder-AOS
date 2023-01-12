package com.koreatech.thunder.feature.thunder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.components.thunderChips
import com.koreatech.thunder.feature.thunder.model.previewHashtagUis

@Preview(showBackground = true)
@Composable
fun pv() {
    ThunderScreen(navController = rememberNavController())
}

@Composable
fun ThunderScreen(
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            ThunderToolBarSlot(
                title = { Text(text = "HanBun") },
                action = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_thunder),
                        contentDescription = ""
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "진행중인 번개")
            Spacer(modifier = Modifier.height(4.dp))
        }
        thunderChips(previewHashtagUis)
    }
}
