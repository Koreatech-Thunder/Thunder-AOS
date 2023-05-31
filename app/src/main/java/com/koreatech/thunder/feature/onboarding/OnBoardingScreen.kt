package com.koreatech.thunder.feature.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderCommonButton
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.SplashState
import com.koreatech.thunder.domain.usecase.SetSplashStateUseCase
import com.koreatech.thunder.navigation.ThunderDestination
import com.koreatech.thunder.navigation.popAndMoveTo

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavController = rememberNavController(),
    setSplashStateUseCase: SetSplashStateUseCase
) {
    val onBoardings = listOf(
        OnBoarding(
            "Welcome to\nHanBun",
            "어디서나 빠르고 간편하게\n즉석 모임을 즐기자구~\n모두들 반가워요!",
            R.drawable.ic_onboarding_thunder
        ),
        OnBoarding(
            "Manner\ntemperature",
            "번개 모임이 끝난 후\n서로에 대한 평가\n놓치지 마세요!!",
            R.drawable.ic_onboarding_temp
        ),
        OnBoarding(
            "HashTag",
            "HashTag를 통해 관심 있는\n번개를 쉽게 찾고\n비슷한 취미를 가진 친구를\n많이 만나보세요~ ",
            R.drawable.ic_onboarding_hashtag
        )
    )
    val state: PagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = state,
            count = 3
        ) { page ->
            OnBoardingItem(
                title = onBoardings[page].title,
                subTitle = onBoardings[page].subTitle,
                image = onBoardings[page].image
            )
        }
        DotsIndicator(
            totalDots = 3,
            selectedIndex = state.currentPage,
            selectedColor = Orange,
            unSelectedColor = Gray,
            toMainScreen = {
                setSplashStateUseCase(SplashState.MAIN)
                navController.popAndMoveTo(ThunderDestination.THUNDER)
            }
        )
    }
}

data class OnBoarding(
    val title: String,
    val subTitle: String,
    @DrawableRes val image: Int
)

@Composable
private fun OnBoardingItem(
    title: String,
    subTitle: String,
    @DrawableRes image: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        BlankSpace(size = 100.dp)
        Text(
            text = title,
            style = ThunderTheme.typography.h1.copy(fontSize = 32.sp)
        )
        BlankSpace(size = 36.dp)
        Text(
            text = subTitle,
            style = ThunderTheme.typography.b2.copy(lineHeight = 24.sp)
        )
        BlankSpace(size = 8.dp)
        Image(
            painter = painterResource(id = image),
            contentDescription = ""
        )
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
    toMainScreen: () -> Unit
) {
    if (selectedIndex != totalDots - 1) {
        LazyRow(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        ) {
            items(totalDots) { index ->
                if (index == selectedIndex) {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(selectedColor)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(unSelectedColor)
                    )
                }

                if (index != totalDots - 1) {
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                }
            }
        }
    } else {
        ThunderCommonButton(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Orange)
                .clickable {
                    toMainScreen()
                }
        ) {
            Text(
                text = stringResource(R.string.on_boarding_next),
                style = ThunderTheme.typography.h4,
                color = Color.White
            )
        }
    }
}
