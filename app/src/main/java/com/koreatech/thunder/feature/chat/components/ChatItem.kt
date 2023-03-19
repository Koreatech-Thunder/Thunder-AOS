package com.koreatech.thunder.feature.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.domain.model.ChatState

@Composable
fun ThunderChatWithProfile(
    name: String,
    chats: List<String>,
    chatState: ChatState
) {
    ThunderChatWithProfileSlot(
        chats = chats,
        chatState = chatState,
        userProfile = {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(8.dp),
                painter = painterResource(id = R.drawable.ic_person),
                contentDescription = ""
            )
        },
        userName = { modifier, textAlign ->
            Text(
                modifier = modifier,
                textAlign = textAlign,
                text = name
            )
        }
    )
}

@Composable
private fun ThunderChatWithProfileSlot(
    chats: List<String>,
    chatState: ChatState,
    userProfile: @Composable () -> Unit,
    userName: @Composable (
        modifier: Modifier,
        textAlign: TextAlign
    ) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (chatState == ChatState.OTHER) Arrangement.Start else Arrangement.End
    ) {
        if (chatState == ChatState.OTHER) {
            Box(contentAlignment = Alignment.Center) {
                userProfile()
            }
            ThunderChatWithNameSlot(chats, chatState, userName)
        } else {
            ThunderChatWithNameSlot(chats, chatState, userName)
            Box(contentAlignment = Alignment.Center) {
                userProfile()
            }
        }
    }
}

@Composable
private fun ThunderChatWithNameSlot(
    chats: List<String>,
    chatState: ChatState,
    userName: @Composable (
        modifier: Modifier,
        textAlign: TextAlign
    ) -> Unit
) {
    Column(modifier = Modifier.width(IntrinsicSize.Min)) {
        userName(
            Modifier.fillMaxWidth()
                .padding(
                    start = if (chatState == ChatState.OTHER) 4.dp else 0.dp,
                    end = if (chatState == ChatState.OTHER) 0.dp else 4.dp
                ),
            if (chatState == ChatState.OTHER) TextAlign.Start else TextAlign.End
        )
        Column(
            modifier = Modifier.padding(4.dp),
            horizontalAlignment = if (chatState == ChatState.OTHER) Alignment.Start else Alignment.End
        ) {
            chats.forEach { chat ->
                ChatFrame(text = chat, chatState = chatState)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThunderChatProfilePreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Orange)
    ) {
        ThunderChatWithProfile(
            name = "KWY",
            chats = listOf("hello", "world"),
            chatState = ChatState.ME
        )
    }
}
