package com.koreatech.thunder.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.Orange200

@Composable
fun RowScope.ThunderBottomBar(
    navController: NavController,
    destinations: List<ThunderDestination>,
    currentDestination: NavDestination?
) {
    destinations.forEach { screen ->
        BottomNavigationItem(
            modifier = Modifier
                .background(Color.White),
            label = {
                Text(
                    text = stringResource(id = screen.title),
                    color = if (currentDestination?.hierarchy?.any { it.route == screen.name } == true) Orange else Orange200
                )
            },
            icon = {
                Image(
                    painter = painterResource(id = screen.icon),
                    contentDescription = stringResource(id = screen.description),
                    colorFilter = if (currentDestination?.hierarchy?.any { it.route == screen.name } == true) {
                        ColorFilter.tint(Orange)
                    } else {
                        ColorFilter.tint(Orange200)
                    }
                )
            },
            selected = currentDestination?.hierarchy?.any { it.route == screen.name } == true,
            onClick = {
                navController.navigate(screen.name) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
