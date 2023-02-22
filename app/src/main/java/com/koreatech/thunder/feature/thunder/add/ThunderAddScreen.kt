package com.koreatech.thunder.feature.thunder.add

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.koreatech.thunder.R
import com.koreatech.thunder.feature.thunder.base.ThunderInputScreen

@Composable
fun ThunderAddScreen(
    navController: NavController = rememberNavController(),
    thunderAddViewModel: ThunderAddViewModel = hiltViewModel()
) {
    val uiState = thunderAddViewModel.uiState.collectAsStateWithLifecycle()
    ThunderInputScreen(
        screenTitle = stringResource(R.string.thunder_add_title),
        uiState = uiState.value,
        navController = navController,
        thunderInputViewModel = thunderAddViewModel
    )
}
