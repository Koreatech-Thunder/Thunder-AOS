package com.koreatech.thunder.feature.chat.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.Chat
import com.koreatech.thunder.domain.model.ChatRoom
import com.koreatech.thunder.domain.model.dummyChatRooms
import com.koreatech.thunder.domain.repository.ChatRepository
import com.koreatech.thunder.socket.SocketHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChatRoomViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val socketHandler: SocketHandler
) : ViewModel() {
    private val onChat: (Chat) -> Unit = { chat ->
        val newChatRooms = chatRooms.value.map { chatRoom ->
            if (chatRoom.id == chat.thunderId) {
                chatRoom.copy(lastChat = chat)
            } else {
                chatRoom
            }
        }
        _chatRooms.value = newChatRooms
    }
    private val _chatRooms: MutableStateFlow<List<ChatRoom>> = MutableStateFlow(emptyList())
    val chatRooms = _chatRooms.asStateFlow()

    init {
        viewModelScope.launch {
            delay(300)
            _chatRooms.value = dummyChatRooms
        }
    }

    fun initSocket() {
        Timber.e("connect chatRoom socket")
        socketHandler.subscribeChatRooms()
        socketHandler.subscribeChat(onChat)
    }

    fun disconnectSocket() {
        Timber.e("disconnect chatRoom socket")
        socketHandler.unSubscribeChatRooms()
        socketHandler.disconnectSocket()
    }

    fun getChatRooms() {
        viewModelScope.launch {
            chatRepository.getChatRooms()
                .onSuccess { result ->
                    _chatRooms.value = result
                }
                .onFailure { throwable ->
                    Timber.e("error ${throwable.message}")
                }
        }
    }
}
