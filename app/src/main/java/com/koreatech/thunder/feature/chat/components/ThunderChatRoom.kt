package com.koreatech.thunder.feature.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Orange100
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.ChatRoom
import com.koreatech.thunder.domain.model.ChatRoomState
import com.koreatech.thunder.util.getIcon

@Composable
fun ThunderChatRoom(
    chatRoom: ChatRoom,
    moveChatDetail: (String) -> Unit,
    moveEvaluate: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(10.dp))
            .background(Orange100)
            .clickable {
                when (chatRoom.chatRoomState) {
                    ChatRoomState.EVALUATE -> moveEvaluate(chatRoom.id)
                    ChatRoomState.RUNNING -> moveChatDetail(chatRoom.id)
                }
            }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        RunningChatRoom(chatRoom = chatRoom)
        if (chatRoom.chatRoomState == ChatRoomState.EVALUATE) {
            Text(
                text = "평가하기",
                style = ThunderTheme.typography.h3
            )
        }
    }
}

@Composable
private fun RunningChatRoom(chatRoom: ChatRoom) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .blur(if (chatRoom.chatRoomState == ChatRoomState.EVALUATE) 4.dp else 0.dp)
            .background(Orange100)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = chatRoom.title,
                style = ThunderTheme.typography.h5
            )
            BlankSpace(size = 18.dp)
            Text(
                text = "${chatRoom.joinMemberCnt}/${chatRoom.limitMemberCnt}",
                style = ThunderTheme.typography.b4,
                color = Gray
            )
        }

        BlankSpace(size = 12.dp)

        chatRoom.lastChat?.let { lastChat ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = lastChat.user.profile.getIcon()),
                    contentDescription = ""
                )
                BlankSpace(size = 18.dp)
                Column {
                    Text(
                        text = lastChat.user.name,
                        style = ThunderTheme.typography.h5
                    )
                    Text(
                        text = lastChat.message,
                        style = ThunderTheme.typography.h6
                    )
                }
            }
        }

        BlankSpace(size = 2.dp)

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${chatRoom.endTime} 종료",
            textAlign = TextAlign.End,
            style = ThunderTheme.typography.b4,
            color = Gray
        )
    }
}
