package com.koreatech.thunder

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            var isShowBottomBar by rememberSaveable { mutableStateOf(true) }

            isShowBottomBar = when (currentDestination?.route) {
                THUNDER.name -> true
                CHAT.name -> true
                PROFILE.name -> true
                else -> false
            }

            Scaffold(
                bottomBar = {
                    AnimatedVisibility(
                        visible = isShowBottomBar,
                        enter = slideInVertically(initialOffsetY = { it }),
                        exit = slideOutVertically(targetOffsetY = { it })
                    ) {
                        BottomNavigation {
                            ThunderBottomBar(navController, bottomItems, currentDestination)
                        }
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
