package com.koreatech.thunder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.koreatech.thunder.domain.usecase.GetSplashStateUseCase
import com.koreatech.thunder.domain.usecase.SetSplashStateUseCase
import com.koreatech.thunder.feature.chat.ChatScreen
import com.koreatech.thunder.feature.onboarding.LoginScreen
import com.koreatech.thunder.feature.onboarding.OnBoardingScreen
import com.koreatech.thunder.feature.onboarding.UserInputScreen
import com.koreatech.thunder.feature.profile.AlarmSettingScreen
import com.koreatech.thunder.feature.profile.ProfileEditScreen
import com.koreatech.thunder.feature.profile.ProfileScreen
import com.koreatech.thunder.feature.profile.ThunderRecordScreen
import com.koreatech.thunder.feature.splash.SplashScreen
import com.koreatech.thunder.feature.thunder.ThunderScreen
import com.koreatech.thunder.feature.thunder.add.ThunderAddScreen
import com.koreatech.thunder.feature.thunder.edit.ThunderEditScreen
import com.koreatech.thunder.navigation.ThunderDestination.ADD
import com.koreatech.thunder.navigation.ThunderDestination.ALARM_SETTING
import com.koreatech.thunder.navigation.ThunderDestination.CHAT
import com.koreatech.thunder.navigation.ThunderDestination.EDIT
import com.koreatech.thunder.navigation.ThunderDestination.LOGIN
import com.koreatech.thunder.navigation.ThunderDestination.ON_BOARDING
import com.koreatech.thunder.navigation.ThunderDestination.PROFILE
import com.koreatech.thunder.navigation.ThunderDestination.PROFILE_EDIT
import com.koreatech.thunder.navigation.ThunderDestination.SPLASH
import com.koreatech.thunder.navigation.ThunderDestination.THUNDER
import com.koreatech.thunder.navigation.ThunderDestination.THUNDER_RECORD
import com.koreatech.thunder.navigation.ThunderDestination.USER_INPUT

@Composable
fun ThunderNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = SPLASH.name,
    getSplashStateUseCase: GetSplashStateUseCase,
    setSplashStateUseCase: SetSplashStateUseCase
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(THUNDER.name) { ThunderScreen(navController = navController) }
        composable(CHAT.name) { ChatScreen(navController = navController) }
        composable(PROFILE.name) { ProfileScreen(navController = navController) }
        composable(ADD.name) { ThunderAddScreen(navController = navController) }
        composable("${EDIT.name}/{thunderId}") { backStackEntry ->
            ThunderEditScreen(
                thunderId = backStackEntry.arguments?.getString("thunderId") ?: "",
                navController = navController
            )
        }
        composable(USER_INPUT.name) { UserInputScreen(navController = navController) }
        composable(ON_BOARDING.name) {
            OnBoardingScreen(
                navController = navController,
                setSplashStateUseCase = setSplashStateUseCase
            )
        }
        composable(PROFILE_EDIT.name) { ProfileEditScreen(navController = navController) }
        composable(THUNDER_RECORD.name) { ThunderRecordScreen(navController = navController) }
        composable(ALARM_SETTING.name) { AlarmSettingScreen(navController = navController) }
        composable(SPLASH.name) {
            SplashScreen(
                navController = navController,
                getSplashStateUseCase = getSplashStateUseCase
            )
        }
        composable(LOGIN.name) { LoginScreen(navController = navController) }
    }
}

fun NavController.popAndMoveTo(thunderDestination: ThunderDestination) {
    navigate(thunderDestination.name) {
        popUpTo(this@popAndMoveTo.graph.id) {
            inclusive = true
        }
    }
}
