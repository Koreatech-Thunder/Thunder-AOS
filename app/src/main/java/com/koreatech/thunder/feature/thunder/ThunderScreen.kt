package com.koreatech.thunder.feature.thunder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderBottomSheet
import com.koreatech.thunder.designsystem.components.ThunderChips
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.dummyThunders
import com.koreatech.thunder.domain.model.dummyUsers
import com.koreatech.thunder.feature.thunder.components.ThunderItem

@Preview(showBackground = true)
@Composable
private fun ThunderScreenPreview() {
    ThunderTheme {
        ThunderScreen(navController = rememberNavController())
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ThunderScreen(
    navController: NavController
) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            ThunderBottomSheet(user = dummyUsers[0])
        }
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
            ThunderChips(Hashtag.values().toList())
            BlankSpace(size = 8.dp)
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(dummyThunders) { thunder ->
                    ThunderItem(thunder = thunder)
                }
            }
        }
    }
}
