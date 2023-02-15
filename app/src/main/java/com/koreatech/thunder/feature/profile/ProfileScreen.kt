package com.koreatech.thunder.feature.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.RoundedLinearIndicator
import com.koreatech.thunder.designsystem.components.ThunderChips
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.Orange200
import com.koreatech.thunder.designsystem.style.ThunderTheme

@Composable
fun ProfileScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileToolBar()
        BlankSpace(size = 12.dp)
        UserDetail()
        BlankSpace(size = 20.dp)
        ServiceSettings()
        ServiceManages()
    }
}

@Composable
private fun ProfileToolBar() {
    ThunderToolBarSlot(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 20.dp, bottom = 16.dp),
        title = {
            Text(
                text = "내 프로필",
                style = ThunderTheme.typography.h3
            )
        }
    )
    Divider(modifier = Modifier.height(1.dp))
}

@Composable
private fun UserDetail() {
    Text(
        text = "자기소개 introduce",
        style = ThunderTheme.typography.h5
    )
    BlankSpace(size = 4.dp)
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "나쁘지 않아요!",
            style = ThunderTheme.typography.h6
        )
        Text(
            text = "36.5",
            style = ThunderTheme.typography.h6
        )
    }
    BlankSpace(size = 12.dp)
    RoundedLinearIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp)
            .height(8.dp),
        progress = 0.60f,
        trackColor = Orange,
        backgroundColor = Orange200
    )
    BlankSpace(size = 12.dp)
    ThunderChips(selectableHashtags = emptyList())
}

@Composable
private fun ServiceSettings() {
}

@Composable
private fun ServiceManages() {
}
