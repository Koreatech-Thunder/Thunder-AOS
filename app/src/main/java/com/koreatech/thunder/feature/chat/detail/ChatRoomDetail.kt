package com.koreatech.thunder.feature.chat.detail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun ChatRoomDetailScreen(
    navController: NavController,
    chatRoomDetailViewModel: ChatRoomDetailViewModel = hiltViewModel()
) {
    val chatRoomDetail = chatRoomDetailViewModel.chatRoomDetail.collectAsStateWithLifecycle()
}
