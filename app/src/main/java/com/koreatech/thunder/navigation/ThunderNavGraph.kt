package com.koreatech.thunder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.koreatech.thunder.feature.chat.ChatScreen
import com.koreatech.thunder.feature.profile.ProfileScreen
import com.koreatech.thunder.feature.thunder.ThunderScreen
import com.koreatech.thunder.navigation.ThunderDestination.CHAT
import com.koreatech.thunder.navigation.ThunderDestination.PROFILE
import com.koreatech.thunder.navigation.ThunderDestination.THUNDER

@Composable
fun ThunderNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = THUNDER.name
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(THUNDER.name) { ThunderScreen(navController = navController) }
        composable(CHAT.name) { ChatScreen(navController = navController) }
        composable(PROFILE.name) { ProfileScreen(navController = navController) }
    }
}
