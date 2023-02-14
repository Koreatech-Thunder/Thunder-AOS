package com.koreatech.thunder.feature.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderChips
import com.koreatech.thunder.designsystem.components.ThunderTextField
import com.koreatech.thunder.designsystem.style.ThunderTheme

@Composable
fun UserInputScreen(
    navController: NavController = rememberNavController()
) {
    Column(
        modifier = Modifier.padding(horizontal = 18.dp)
    ) {
        Text(
            text = "회원 정보 입력",
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
            text = "관심 있는 카테고리를 선택해 주세요!",
            style = ThunderTheme.typography.h3
        )
        BlankSpace(size = 16.dp)
        ThunderChips(
            modifier = Modifier.weight(1f),
            selectableHashtags = emptyList(),
            selectHashtag = {}
        )
        
        Box(
            modifier = Modifier
        ) {
            Text(text = "다음으로")
        }
    }
}
