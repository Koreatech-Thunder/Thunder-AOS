package com.koreatech.thunder.feature.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.Chat
import com.koreatech.thunder.domain.model.ChatState
import com.koreatech.thunder.domain.model.ChatState.ME
import com.koreatech.thunder.domain.model.ChatState.OTHER
import com.koreatech.thunder.domain.model.User

@Composable
fun ChatItem(chat: Chat) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (chat.state == ME) Alignment.End else Alignment.Start
    ) {
        ChatProfile(
            user = chat.user,
            chatState = chat.state
        )
        BlankSpace(size = 4.dp)
        ChatWithTime(
            message = chat.message,
            chatTime = chat.createdAt,
            chatState = chat.state
        )
    }
}

@Composable
fun ChatProfile(
    user: User,
    chatState: ChatState
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (chatState == ME) {
            Text(
                text = user.name,
                style = ThunderTheme.typography.b4
            )
        }
        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(8.dp),
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = ""
        )
        if (chatState == OTHER) {
            Text(
                text = user.name,
                style = ThunderTheme.typography.b4
            )
        }
    }
}

@Composable
fun ChatWithTime(
    message: String,
    chatTime: String,
    chatState: ChatState
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        if (chatState == ME) {
            Text(
                text = chatTime,
                style = ThunderTheme.typography.b5
            )
        }
        ChatFrame(
            text = message,
            chatState = chatState
        )
        if (chatState == OTHER) {
            Text(
                text = chatTime,
                style = ThunderTheme.typography.b5
            )
        }
    }
}

@Composable
fun ChatFrame(
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
            textAlign = if (chatState == OTHER) TextAlign.Start else TextAlign.End,
            style = ThunderTheme.typography.b4
        )
    }
}
