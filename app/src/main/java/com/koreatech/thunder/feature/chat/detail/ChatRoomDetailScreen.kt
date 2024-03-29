package com.koreatech.thunder.feature.chat.detail

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.ThunderTextField
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.Orange100
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.feature.chat.components.ChatGuideItem
import com.koreatech.thunder.feature.chat.components.ChatItem
import com.koreatech.thunder.feature.thunder.UiEvent
import com.koreatech.thunder.feature.thunder.components.ReportDialog
import com.koreatech.thunder.feature.thunder.components.noRippleClickable
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun ChatRoomDetailScreen(
    navController: NavController,
    thunderId: String,
    chatRoomDetailViewModel: ChatRoomDetailViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    context: Context = LocalContext.current
) {
    val chatRoomDetail = chatRoomDetailViewModel.chatRoomDetail.collectAsStateWithLifecycle()
    val chat = chatRoomDetailViewModel.chat.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    var isReportDialogVisible by remember { mutableStateOf(false) }

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

    LaunchedEffect(true) {
        chatRoomDetailViewModel.uiEvent.flowWithLifecycle(lifecycleOwner.lifecycle).onEach {
            handleUiEvent(context, it)
        }.launchIn(lifecycleOwner.lifecycleScope)
    }

    if (isReportDialogVisible) {
        ReportDialog(
            onDismissRequest = { isReportDialogVisible = false },
            reportUser = chatRoomDetailViewModel::reportChat
        )
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
            item { ChatGuideItem() }
            itemsIndexed(chatRoomDetail.value.chats) { index, chat ->
                ChatItem(
                    beforeUserId = if (index > 0) chatRoomDetail.value.chats[index - 1].user.userId else "",
                    chat = chat,
                    showDialog = { thunderId, chatId ->
                        chatRoomDetailViewModel.setReportInfo(thunderId, chatId)
                        isReportDialogVisible = true
                    }
                )
            }
        }
        ChatTextField(
            chat = chat.value,
            writeChat = chatRoomDetailViewModel::writeChat,
            sendChat = chatRoomDetailViewModel::sendChat,
            modifier = Modifier.imePadding()
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
    modifier: Modifier,
    chat: String,
    writeChat: (String) -> Unit,
    sendChat: () -> Unit
) {
    Row(
        modifier = modifier
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

private fun handleUiEvent(context: Context, uiEvent: UiEvent) {
    val toastText = when (uiEvent) {
        UiEvent.REPORT_SUCCESS -> context.getString(R.string.chat_report_success)
        UiEvent.NETWORK_FAIL -> context.getString(R.string.network_fail)
        else -> ""
    }
    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
}
