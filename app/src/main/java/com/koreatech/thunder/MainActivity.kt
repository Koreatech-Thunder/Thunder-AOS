package com.koreatech.thunder

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.usecase.GetSplashStateUseCase
import com.koreatech.thunder.domain.usecase.SetSplashStateUseCase
import com.koreatech.thunder.navigation.ThunderBottomBar
import com.koreatech.thunder.navigation.ThunderDestination.*
import com.koreatech.thunder.navigation.ThunderNavHost
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var getSplashStateUseCase: GetSplashStateUseCase

    @Inject
    lateinit var setSplashStateUseCase: SetSplashStateUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        checkAndroid13()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            var isShowBottomBar by rememberSaveable { mutableStateOf(true) }

            isShowBottomBar = when (currentDestination?.route) {
                THUNDER.name -> true
                CHATROOM.name -> true
                PROFILE.name -> true
                else -> false
            }

            Scaffold(
                modifier = Modifier
                    .statusBarsPadding()
                    .navigationBarsPadding(),
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
                        modifier = Modifier.padding(innerPadding),
                        getSplashStateUseCase = getSplashStateUseCase,
                        setSplashStateUseCase = setSplashStateUseCase,
                        moveOpenSourceLicense = {
                            startActivity(Intent(this, OssLicensesMenuActivity::class.java))
                        }
                    )
                }
            }
        }
    }

    private fun checkAndroid13() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.POST_NOTIFICATIONS
                    )
                ) {
                    val intent: Intent =
                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.parse("package:" + this.packageName))
                    startActivity(intent)
                    finish()
                } else {
                    // 처음 권한 요청을 할 경우
                    registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                        when (it) {
                            true -> {
                            }
                            false -> {
                                moveTaskToBack(true)
                                finishAndRemoveTask()
                                exitProcess(0)
                            }
                        }
                    }.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }

    }

    companion object {
        private val bottomItems = listOf(
            THUNDER,
            CHATROOM,
            PROFILE
        )
    }
}
