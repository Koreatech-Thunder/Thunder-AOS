package com.koreatech.thunder.feature.chat.room

import androidx.lifecycle.ViewModel
import com.koreatech.thunder.domain.model.ChatRoom
import com.koreatech.thunder.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ChatRoomViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {
    private val _chatRooms: MutableStateFlow<List<ChatRoom>> = MutableStateFlow(emptyList())
    val chatRooms = _chatRooms.asStateFlow()
}
