package com.koreatech.thunder.feature.chat.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.ThunderTextField
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.Orange100
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.feature.chat.components.ChatItem
import com.koreatech.thunder.feature.thunder.components.noRippleClickable

@Composable
fun ChatRoomDetailScreen(
    navController: NavController,
    thunderId: String,
    chatRoomDetailViewModel: ChatRoomDetailViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val chatRoomDetail = chatRoomDetailViewModel.chatRoomDetail.collectAsStateWithLifecycle()
    val chat = chatRoomDetailViewModel.chat.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                chatRoomDetailViewModel.getChatRoomDetail(thunderId)
                chatRoomDetailViewModel.initSocket(thunderId)
            } else if (event == Lifecycle.Event.ON_STOP) {
                chatRoomDetailViewModel.disconnectSocket()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(chatRoomDetail.value.chats.size) {
        listState.animateScrollToItem(chatRoomDetail.value.chats.size)
    }

    Column {
        ChatRoomDetailToolbar(
            title = chatRoomDetail.value.title,
            memberStatus = "${chatRoomDetail.value.joinMemberCnt}/${chatRoomDetail.value.limitMemberCnt}",
            isAlarm = chatRoomDetail.value.isAlarm,
            navigationAction = {
                chatRoomDetailViewModel.disconnectSocket()
                navController.popBackStack()
            },
            postfixAction = {
                chatRoomDetailViewModel.setAlarmState()
            }
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .background(Orange100),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(8.dp),
            state = listState
        ) {
            itemsIndexed(chatRoomDetail.value.chats) { index, chat ->
                ChatItem(
                    beforeUserId = if (index > 0) chatRoomDetail.value.chats[index - 1].user.userId else "",
                    chat = chat
                )
            }
        }
        ChatTextField(
            chat = chat.value,
            writeChat = chatRoomDetailViewModel::writeChat,
            sendChat = chatRoomDetailViewModel::sendChat
        )
    }
}

@Composable
private fun ChatRoomDetailToolbar(
    title: String,
    memberStatus: String,
    isAlarm: Boolean,
    navigationAction: () -> Unit,
    postfixAction: () -> Unit
) {
    ThunderToolBarSlot(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 18.dp),
        navigationIcon = {
            Image(
                modifier = Modifier.noRippleClickable { navigationAction() },
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = ""
            )
        },
        title = {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = title,
                    style = ThunderTheme.typography.h4
                )
                Text(
                    text = memberStatus,
                    style = ThunderTheme.typography.b4,
                    color = Gray
                )
            }
        },
        action = {
            Icon(
                modifier = Modifier.noRippleClickable { postfixAction() },
                painter = if (isAlarm) painterResource(id = R.drawable.ic_notifications_on)
                else painterResource(id = R.drawable.ic_notifications),
                contentDescription = ""
            )
        }
    )
    Divider(modifier = Modifier.height(1.dp))
}

@Composable
private fun ChatTextField(
    chat: String,
    writeChat: (String) -> Unit,
    sendChat: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ThunderTextField(
            modifier = Modifier
                .weight(1f),
            text = chat,
            hint = "",
            onTextChange = writeChat
        )
        Text(
            modifier = Modifier
                .clickable(chat.isNotEmpty()) {
                    sendChat()
                }
                .padding(4.dp),
            text = stringResource(R.string.chat_send),
            style = ThunderTheme.typography.b5,
            color = if (chat.isEmpty()) Gray else Orange
        )
    }
}
