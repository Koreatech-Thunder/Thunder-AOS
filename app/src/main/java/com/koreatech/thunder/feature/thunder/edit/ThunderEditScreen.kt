package com.koreatech.thunder.feature.thunder.edit

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.koreatech.thunder.R
import com.koreatech.thunder.feature.thunder.base.ThunderInputScreen

@Composable
fun ThunderEditScreen(
    thunderId: String,
    navController: NavController = rememberNavController(),
    thunderEditViewModel: ThunderEditViewModel = hiltViewModel()
) {
    val uiState = thunderEditViewModel.uiState.collectAsStateWithLifecycle()
    ThunderInputScreen(
        screenTitle = stringResource(R.string.thunder_edit_title),
        uiState = uiState.value,
        navController = navController,
        thunderInputViewModel = thunderEditViewModel
    )
}
