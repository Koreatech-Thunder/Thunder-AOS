package com.koreatech.thunder.feature.chat.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.Chat
import com.koreatech.thunder.domain.model.ChatRoomDetail
import com.koreatech.thunder.domain.model.dummyChats
import com.koreatech.thunder.domain.repository.ChatRepository
import com.koreatech.thunder.socket.SocketHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class ChatRoomDetailViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val socketHandler: SocketHandler
) : ViewModel() {
    private val onChat: (Chat) -> Unit = { chat ->
        _chatRoomDetail.value =
            chatRoomDetail.value.copy(chats = chatRoomDetail.value.chats.plus(chat))
    }
    private val chatRoomId = MutableStateFlow("")
    private val _chatRoomDetail =
        MutableStateFlow(ChatRoomDetail("농구할 사람", 8, 4, emptyList(), true))
    private val _chat = MutableStateFlow("")
    val chatRoomDetail = _chatRoomDetail.asStateFlow()
    val chat = _chat.asStateFlow()

    init {
        viewModelScope.launch {
            delay(100)
            _chatRoomDetail.value = ChatRoomDetail("농구할 사람", 8, 4, dummyChats, true)
        }
    }

    fun initSocket(thunderId: String) {
        chatRoomId.value = thunderId
        socketHandler.connectSocket()
        socketHandler.subscribeChatRoom(chatRoomId.value)
        socketHandler.subscribeChat(onChat)
    }

    fun disconnectSocket() {
        socketHandler.unSubscribeChatRoom(chatRoomId.value)
        socketHandler.disConnectSocket()
    }

    fun getChatRoomDetail(chatId: String) {
        viewModelScope.launch {
            chatRepository.getChatRoomDetail(chatId)
                .onSuccess { result ->
                    _chatRoomDetail.value = result
                }
                .onFailure { throwable ->
                    Timber.e("error ${throwable.message}")
                }
        }
    }

    fun writeChat(chat: String) {
        _chat.value = chat
    }
}
