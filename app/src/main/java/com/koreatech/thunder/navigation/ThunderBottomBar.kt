package com.koreatech.thunder.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.koreatech.thunder.designsystem.style.Orange

@Composable
fun RowScope.ThunderBottomBar(
    navController: NavController,
    destinations: List<ThunderDestination>,
    currentDestination: NavDestination?
) {
    destinations.forEach { screen ->
        BottomNavigationItem(
            modifier = Modifier.background(Orange),
            label = { Text(text = stringResource(id = screen.title)) },
            icon = {
                Icon(
                    painter = painterResource(id = screen.icon),
                    contentDescription = stringResource(id = screen.description)
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
