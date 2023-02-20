package com.koreatech.thunder.feature.thunder.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.designsystem.components.ThunderDialogSlot
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme

@Composable
fun CreateChatDialog(
    onDismissRequest: () -> Unit
) {
    ThunderDialogSlot(
        radius = 20.dp,
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .padding(
                    vertical = 24.dp,
                    horizontal = 32.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "채팅방이 생겼어요!",
                style = ThunderTheme.typography.h2
            )
            Text(
                text = "이 채팅방은 번개가 끝난 후\n하루 뒤에 없어 진다는 점 명심해 주세요~",
                style = ThunderTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Text(
                text = "금전적 거래는 번개 채팅방에서 자제!\n채팅방에서는 서로에게 매너 있는 모습!\n채팅방이 종료된 후, 서로에 대한 매너 평가까지!",
                style = ThunderTheme.typography.b3,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .noRippleClickable { onDismissRequest() },
                color = Orange,
                text = "확인",
                style = ThunderTheme.typography.h5
            )
        }
    }
}
