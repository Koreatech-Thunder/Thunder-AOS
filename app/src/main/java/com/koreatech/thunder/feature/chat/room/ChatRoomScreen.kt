package com.koreatech.thunder.feature.chat.room

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
    chatRoomViewModel: ChatRoomViewModel = hiltViewModel()
) {
    val chatRooms = chatRoomViewModel.chatRooms.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        chatRoomViewModel.initSocket()
        chatRoomViewModel.getChatRooms()
    }

    Column {
        ChatRoomToolBar()
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(chatRooms.value) { chatRoom ->
                ThunderChatRoom(
                    chatRoom = chatRoom,
                    moveChatDetail = { thunderId ->
                        chatRoomViewModel.disconnectSocket()
                        navController.navigate("${ThunderDestination.CHAT_DETAIL.name}/$thunderId")
                    },
                    moveEvaluate = { thunderId ->
                        chatRoomViewModel.disconnectSocket()
                        navController.navigate("${ThunderDestination.EVALUATE.name}/$thunderId")
                    }
                )
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
