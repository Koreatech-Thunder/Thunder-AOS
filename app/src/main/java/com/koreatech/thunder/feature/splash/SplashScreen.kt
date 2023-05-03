package com.koreatech.thunder.feature.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.koreatech.thunder.domain.model.SplashState
import com.koreatech.thunder.domain.usecase.GetSplashStateUseCase
import com.koreatech.thunder.navigation.ThunderDestination.*
import com.koreatech.thunder.navigation.popAndMoveTo
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    getSplashStateUseCase: GetSplashStateUseCase
) {
    LaunchedEffect(true) {
        delay(2000)
        navController.popBackStack()
        when (getSplashStateUseCase()) {
            SplashState.LOGIN -> navController.popAndMoveTo(LOGIN)
            SplashState.ON_BOARDING -> navController.popAndMoveTo(ON_BOARDING)
            SplashState.USER_INPUT -> navController.popAndMoveTo(USER_INPUT)
            SplashState.MAIN -> navController.popAndMoveTo(THUNDER)
            SplashState.WARNING -> navController.popAndMoveTo(WARNING)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "SPLASH")
    }
}
