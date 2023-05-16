package com.koreatech.thunder.feature.chat.room

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.feature.chat.components.ThunderChatRoom
import com.koreatech.thunder.navigation.ThunderDestination

@Composable
fun ChatRoomScreen(
    navController: NavController,
    chatRoomViewModel: ChatRoomViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val chatRooms = chatRoomViewModel.chatRooms.collectAsStateWithLifecycle()
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                chatRoomViewModel.initSocket()
                chatRoomViewModel.getChatRooms()
            } else if (event == Lifecycle.Event.ON_STOP) {
                chatRoomViewModel.disconnectSocket()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column {
        ChatRoomToolBar()
        if (chatRooms.value.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        val thunderId = "thunderId"
                        navController.navigate("${ThunderDestination.CHAT_DETAIL.name}/$thunderId")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.chatroom_empty),
                    style = ThunderTheme.typography.b3
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(chatRooms.value) { chatRoom ->
                    ThunderChatRoom(
                        chatRoom = chatRoom,
                        moveChatDetail = { thunderId ->
                            navController.navigate("${ThunderDestination.CHAT_DETAIL.name}/$thunderId")
                        },
                        moveEvaluate = { thunderId ->
                            navController.navigate("${ThunderDestination.EVALUATE.name}/$thunderId")
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ChatRoomToolBar() {
    ThunderToolBarSlot(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 20.dp, bottom = 16.dp),
        title = {
            Text(
                text = stringResource(R.string.chat_room_title),
                style = ThunderTheme.typography.h3
            )
        }
    )
    Divider(modifier = Modifier.height(1.dp))
}
