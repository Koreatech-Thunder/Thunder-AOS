package com.koreatech.thunder.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.SplashState
import com.koreatech.thunder.domain.usecase.GetSplashStateUseCase
import com.koreatech.thunder.navigation.ThunderDestination
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
            SplashState.LOGIN -> navController.popAndMoveTo(ThunderDestination.LOGIN)
            SplashState.ON_BOARDING -> navController.popAndMoveTo(ThunderDestination.ON_BOARDING)
            SplashState.USER_INPUT -> navController.popAndMoveTo(ThunderDestination.USER_INPUT)
            SplashState.MAIN -> navController.popAndMoveTo(ThunderDestination.THUNDER)
            SplashState.WARNING -> navController.popAndMoveTo(ThunderDestination.WARNING)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 84.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(id = R.drawable.ic_thunder_logo),
            contentDescription = ""
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "HANBUN",
            style = ThunderTheme.typography.h1,
            textAlign = TextAlign.Center
        )
    }
}
