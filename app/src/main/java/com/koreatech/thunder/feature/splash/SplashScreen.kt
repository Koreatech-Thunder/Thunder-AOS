package com.koreatech.thunder.feature.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.koreatech.thunder.domain.model.SplashState
import com.koreatech.thunder.navigation.ThunderDestination.LOGIN
import com.koreatech.thunder.navigation.ThunderDestination.ON_BOARDING
import com.koreatech.thunder.navigation.ThunderDestination.THUNDER
import com.koreatech.thunder.navigation.ThunderDestination.USER_INPUT
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        delay(2000)
        when (splashViewModel.getSplashState()) {
            SplashState.LOGIN -> navController.navigate(LOGIN.name)
            SplashState.ON_BOARDING -> navController.navigate(ON_BOARDING.name)
            SplashState.USER_INPUT -> navController.navigate(USER_INPUT.name)
            SplashState.MAIN -> navController.navigate(THUNDER.name)
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
