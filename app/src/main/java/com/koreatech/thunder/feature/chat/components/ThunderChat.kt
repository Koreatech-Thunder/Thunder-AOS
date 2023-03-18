package com.koreatech.thunder.feature.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.domain.model.ChatState
import com.koreatech.thunder.domain.model.ChatState.ME
import com.koreatech.thunder.domain.model.ChatState.OTHER

@Composable
fun ThunderChat(
    modifier: Modifier = Modifier,
    text: String,
    chatState: ChatState
) {
    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = if (chatState == OTHER) 4.dp else 16.dp,
                    topEnd = if (chatState == OTHER) 16.dp else 4.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .background(Color.White)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            textAlign = if (chatState == OTHER) TextAlign.Start else TextAlign.End
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ThunderChatFramePreview() {
    Column(
        modifier = Modifier.fillMaxSize().background(color = Orange)
    ) {
        ThunderChat(text = "Hello World", chatState = OTHER)
        ThunderChat(text = "Hello World", chatState = ME)
    }
}
