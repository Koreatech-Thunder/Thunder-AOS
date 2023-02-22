package com.koreatech.thunder.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.style.KakaoBrown
import com.koreatech.thunder.designsystem.style.KakaoYellow
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.SplashState
import com.koreatech.thunder.navigation.ThunderDestination
import com.koreatech.thunder.navigation.popAndMoveTo

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.app_name),
            style = ThunderTheme.typography.h1,
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(KakaoYellow)
                .clickable {
                    /*
                    * 로그인 하기 로직 구현
                    * */
//                    loginViewModel.postLogin(context)
                    loginViewModel.setSplashState(SplashState.USER_INPUT)
                    navController.popAndMoveTo(ThunderDestination.USER_INPUT)
                }
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                text = stringResource(R.string.kakao_login),
                color = KakaoBrown,
                style = ThunderTheme.typography.b2,
                textAlign = TextAlign.Center
            )
        }
        BlankSpace(size = 24.dp)
    }
}
