package com.koreatech.thunder.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderChips
import com.koreatech.thunder.designsystem.components.ThunderCommonButton
import com.koreatech.thunder.designsystem.components.ThunderTextField
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme

@Composable
fun UserInputScreen(
    navController: NavController = rememberNavController()
) {
    Column(
        modifier = Modifier.padding(horizontal = 18.dp)
    ) {
        Text(
            text = stringResource(R.string.user_input_title),
            style = ThunderTheme.typography.h3
        )
        BlankSpace(size = 88.dp)

        Text(
            text = stringResource(R.string.profile_nickname),
            style = ThunderTheme.typography.h3
        )
        BlankSpace(size = 24.dp)
        ThunderTextField(
            modifier = Modifier,
            text = "",
            hint = stringResource(R.string.profile_nickname_hint),
            limitTextCount = 120,
            onTextChange = {}
        )
        BlankSpace(size = 30.dp)

        Text(
            text = stringResource(R.string.profile_hashtag_title),
            style = ThunderTheme.typography.h3
        )
        BlankSpace(size = 8.dp)
        Text(
            text = stringResource(R.string.user_input_hashtag),
            style = ThunderTheme.typography.h3
        )
        BlankSpace(size = 16.dp)
        ThunderChips(
            modifier = Modifier.weight(1f),
            selectableHashtags = emptyList(),
            selectHashtag = {}
        )

        ThunderCommonButton(
            modifier = Modifier
                .clickable { }
                .clip(RoundedCornerShape(8.dp))
                .background(if (true) Orange else Gray),
            buttonText = {
                Text(
                    text = stringResource(R.string.user_input_btn),
                    style = ThunderTheme.typography.h4,
                    color = Color.White
                )
            }
        )
    }
}
