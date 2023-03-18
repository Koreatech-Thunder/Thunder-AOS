package com.koreatech.thunder.feature.chat.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.ThunderTextField
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme

@Composable
fun ChatRoomDetailScreen(
    navController: NavController,
    chatRoomDetailViewModel: ChatRoomDetailViewModel = hiltViewModel()
) {
    val chatRoomDetail = chatRoomDetailViewModel.chatRoomDetail.collectAsStateWithLifecycle()
    val chat = chatRoomDetailViewModel.chat.collectAsStateWithLifecycle()

    Column {
        ChatRoomDetailToolbar(
            title = chatRoomDetail.value.title,
            memberStatus = "${chatRoomDetail.value.joinMemberCnt}/${chatRoomDetail.value.limitMemberCnt}",
            isAlarm = chatRoomDetail.value.isAlarm
        )
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(chatRoomDetail.value.chats) {
            }
        }
        ChatTextField(
            chat = chat.value,
            writeChat = chatRoomDetailViewModel::writeChat
        )
    }
}

@Composable
private fun ChatRoomDetailToolbar(
    title: String,
    memberStatus: String,
    isAlarm: Boolean
) {
    ThunderToolBarSlot(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 18.dp),
        navigationIcon = {
            Text(
                text = title,
                style = ThunderTheme.typography.h4
            )
        },
        title = {
            Text(
                text = memberStatus,
                style = ThunderTheme.typography.b4,
                color = Gray
            )
        },
        action = {
            if (isAlarm) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notifications_on),
                    contentDescription = ""
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notifications),
                    contentDescription = ""
                )
            }
        }
    )
    Divider(modifier = Modifier.height(1.dp))
}

@Composable
private fun ChatTextField(
    chat: String,
    writeChat: (String) -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ThunderTextField(
            modifier = Modifier.weight(1f),
            text = chat,
            hint = "",
            onTextChange = writeChat
        )
        Text(
            modifier = Modifier
                .clickable(chat.isNotEmpty()) {
                    /* 채팅 보내기 */
                }
                .padding(4.dp),
            text = stringResource(R.string.chat_send),
            style = ThunderTheme.typography.b5,
            color = if (chat.isEmpty()) Gray else Orange
        )
    }
}