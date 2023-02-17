package com.koreatech.thunder.feature.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderDialogSlot
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.feature.thunder.components.noRippleClickable

@Composable
fun WithDrawDialog(
    userName: String,
    onDismissRequest: () -> Unit
) {
    ThunderDialogSlot(
        radius = 20.dp,
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "$userName 님,\n잠시만요!",
                style = ThunderTheme.typography.h2
            )
            BlankSpace(size = 12.dp)
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "정말 탈퇴하시겠어요?",
                style = ThunderTheme.typography.h2,
                textAlign = TextAlign.Center
            )
            BlankSpace(size = 36.dp)
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "탈퇴하시면,\n- 그동안 모아두었던 매너 온도가 사라집니다.\n- 참여했던 번개 내역이 모두 없어집니다.\n- 탈퇴 후에는 전의 계정을 다시 살리거나 채팅, 피드 등의 데이터를 복구할 수 없습니다.",
                style = ThunderTheme.typography.b3
            )
            BlankSpace(size = 36.dp)
            Text(
                modifier = Modifier
                    .noRippleClickable { onDismissRequest() }
                    .fillMaxWidth(),
                color = Orange,
                text = "탈퇴하기",
                style = ThunderTheme.typography.h5,
                textAlign = TextAlign.Center
            )
        }
    }
}
