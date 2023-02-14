package com.koreatech.thunder.feature.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderChips
import com.koreatech.thunder.designsystem.components.ThunderRowSpaceBetweenSlot
import com.koreatech.thunder.designsystem.components.ThunderTextField
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.Orange200
import com.koreatech.thunder.designsystem.style.ThunderTheme

@Composable
fun UserProfileScreen() {
    Column {
        ThunderRowSpaceBetweenSlot(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 18.dp),
            prefixComponent = {
                Text(
                    text = "회원 정보 입력",
                    style = ThunderTheme.typography.h2
                )
            },
            postfixComponent = {
                Text(
                    text = "저장",
                    style = ThunderTheme.typography.h2,
                    color = if (true) Orange200 else Orange
                )
            }
        )
        BlankSpace(size = 88.dp)
        Text(
            modifier = Modifier.padding(start = 18.dp),
            text = "닉네임",
            style = ThunderTheme.typography.h3
        )
        BlankSpace(size = 24.dp)
        ThunderTextField(
            modifier = Modifier,
            text = "",
            hint = "Hanbun에서 사용할 이름을 입력해주세요.",
            limitTextCount = 120,
            onTextChange = {}
        )
        BlankSpace(size = 36.dp)
        Text(
            modifier = Modifier.padding(start = 18.dp),
            text = "카테고리",
            style = ThunderTheme.typography.h3
        )
        BlankSpace(size = 8.dp)
        Text(
            modifier = Modifier.padding(start = 18.dp),
            text = "관심 있는 카테고리를 선택해 주세요!",
            color = Gray,
            style = ThunderTheme.typography.b3
        )
        BlankSpace(size = 16.dp)
        ThunderChips(
            selectableHashtags = emptyList(),
            selectHashtag = {}
        )
    }
}
