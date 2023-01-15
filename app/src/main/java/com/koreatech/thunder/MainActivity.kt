package com.koreatech.thunder

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.navigation.ThunderBottomBar
import com.koreatech.thunder.navigation.ThunderDestination.CHAT
import com.koreatech.thunder.navigation.ThunderDestination.PROFILE
import com.koreatech.thunder.navigation.ThunderDestination.THUNDER
import com.koreatech.thunder.navigation.ThunderNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    BottomNavigation {
                        ThunderBottomBar(navController, bottomItems, currentDestination)
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        backgroundColor = Orange,
                        contentColor = Color.White,
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = null
                        )
                    }
                }
            ) { innerPadding ->
                ThunderTheme {
                    ThunderNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    companion object {
        private val bottomItems = listOf(
            THUNDER,
            CHAT,
            PROFILE
        )
    }
}
