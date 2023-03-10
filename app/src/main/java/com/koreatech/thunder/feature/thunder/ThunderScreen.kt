package com.koreatech.thunder.feature.thunder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderBottomSheet
import com.koreatech.thunder.designsystem.components.ThunderChips
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.User
import com.koreatech.thunder.domain.model.dummyThunders
import com.koreatech.thunder.feature.thunder.components.ReportDialog
import com.koreatech.thunder.feature.thunder.components.ThunderItem
import com.koreatech.thunder.navigation.ThunderDestination
import kotlinx.coroutines.launch

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
    navController: NavController,
    thunderViewModel: ThunderViewModel = hiltViewModel()
) {
    val thunderUiState = thunderViewModel.thunderUiState.collectAsStateWithLifecycle()
    val hashtagUiState = thunderViewModel.hashtagUiState.collectAsStateWithLifecycle()
    val userInfo = thunderViewModel.userInfo.collectAsStateWithLifecycle()
    var isReportDialogVisible by remember { mutableStateOf(false) }
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    val showBottomSheet: (User) -> Unit = { user ->
        coroutineScope.launch {
            thunderViewModel.setUser(user)
            bottomSheetState.show()
        }
    }

    if (isReportDialogVisible) {
        ReportDialog(
            onDismissRequest = { isReportDialogVisible = false },
            reportUser = thunderViewModel::reportUser
        )
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            ThunderBottomSheet(
                user = userInfo.value,
                showReportDialog = { isReportDialogVisible = true }
            )
        }
    ) {
        Scaffold(
            modifier = Modifier.padding(horizontal = 16.dp),
            floatingActionButton = {
                FloatingActionButton(
                    backgroundColor = Orange,
                    contentColor = Color.White,
                    onClick = {
                        navController.navigate(route = ThunderDestination.ADD.name)
                    }
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = null
                    )
                }
            }
        ) { innerPadding ->
            Column(modifier = Modifier.fillMaxWidth().padding(innerPadding)) {
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
                when (val state = hashtagUiState.value) {
                    HashtagUiState.Error -> TODO()
                    HashtagUiState.Loading -> TODO()
                    is HashtagUiState.Success -> {
                        ThunderChips(
                            selectableHashtags = state.hashtags,
                            selectHashtag = thunderViewModel::selectHashtag,
                            isClickable = true
                        )
                    }
                }
                BlankSpace(size = 8.dp)
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(dummyThunders) { thunder ->
                        ThunderItem(
                            thunder = thunder,
                            showBottomSheet = showBottomSheet,
                            participateThunder = { /* ?????? ???????????? */ },
                            cancelThunder = { /* ?????? ???????????? */ },
                            moveToEdit = { id ->
                                navController.navigate("${ThunderDestination.EDIT.name}/$id")
                            }
                        )
                    }
                }
            }
        }
    }
}
