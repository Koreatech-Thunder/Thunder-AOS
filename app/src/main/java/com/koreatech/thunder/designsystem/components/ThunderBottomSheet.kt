package com.koreatech.thunder.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.Orange200
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.User

@Composable
fun ThunderBottomSheet(
    user: User
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .padding(20.dp)
    ) {
        Column {
            ThunderRowSpaceBetweenSlot(
                verticalAlignment = Alignment.Top,
                prefixComponent = {
                    ProfileDetail(user)
                }
            )
            BlankSpace(size = 12.dp)
            Text(
                text = user.introduction,
                style = ThunderTheme.typography.h5
            )
            BlankSpace(size = 4.dp)
            TemperatureDetail(user)
            BlankSpace(size = 12.dp)
            RoundedLinearIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp)
                    .height(8.dp),
                progress = user.temperature / 100f,
                trackColor = Orange,
                backgroundColor = Orange200
            )
            BlankSpace(size = 12.dp)
            ThunderChips(selectableHashtags = user.hashtags)
        }
    }
}

@Composable
private fun ProfileDetail(userUi: User) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(65.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_smile_faces),
                contentDescription = ""
            )
        }
        BlankSpace(size = 20.dp)
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = userUi.name,
                style = ThunderTheme.typography.h3
            )
            Text(
                text = userUi.userId,
                style = ThunderTheme.typography.h6
            )
        }
    }
}

@Composable
private fun TemperatureDetail(userUi: User) {
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
            text = "${userUi.temperature}",
            style = ThunderTheme.typography.h6
        )
    }
}
