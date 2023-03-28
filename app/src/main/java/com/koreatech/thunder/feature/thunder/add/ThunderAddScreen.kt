package com.koreatech.thunder.feature.thunder.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.koreatech.thunder.R
import com.koreatech.thunder.feature.thunder.base.ThunderInputScreen
import com.koreatech.thunder.navigation.ThunderDestination
import com.koreatech.thunder.navigation.popAndMoveTo
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun ThunderAddScreen(
    navController: NavController = rememberNavController(),
    thunderAddViewModel: ThunderAddViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val uiState = thunderAddViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        thunderAddViewModel.moveDestination.flowWithLifecycle(lifecycleOwner.lifecycle)
            .onEach { destination ->
                when (destination) {
                    ThunderDestination.THUNDER -> navController.popAndMoveTo(ThunderDestination.THUNDER)
                    else -> {}
                }
            }.launchIn(lifecycleOwner.lifecycleScope)
    }

    ThunderInputScreen(
        screenTitle = stringResource(R.string.thunder_add_title),
        uiState = uiState.value,
        navController = navController,
        thunderInputViewModel = thunderAddViewModel
    )
}
