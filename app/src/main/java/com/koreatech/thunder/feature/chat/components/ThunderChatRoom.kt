package com.koreatech.thunder.feature.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Orange100
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.ChatRoom

@Composable
fun ThunderChatRoom(
    chatRoom: ChatRoom
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Orange100)
            .padding(16.dp)
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
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_smile_faces),
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
