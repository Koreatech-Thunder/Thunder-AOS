package com.koreatech.thunder.feature.thunder.edit

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.koreatech.thunder.feature.thunder.base.ThunderInputScreen

@Composable
fun ThunderEditScreen(
    navController: NavController = rememberNavController(),
    thunderEditViewModel: ThunderEditViewModel = hiltViewModel()
) {
    val uiState = thunderEditViewModel.uiState.collectAsStateWithLifecycle()
    ThunderInputScreen(
        uiState = uiState.value,
        navController = navController,
        thunderInputViewModel = thunderEditViewModel
    )
}
