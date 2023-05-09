package com.koreatech.thunder.feature.chat.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.Chat
import com.koreatech.thunder.domain.model.ChatRoomDetail
import com.koreatech.thunder.domain.model.dummyChats
import com.koreatech.thunder.domain.repository.ChatRepository
import com.koreatech.thunder.feature.thunder.UiEvent
import com.koreatech.thunder.socket.SocketHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChatRoomDetailViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val socketHandler: SocketHandler
) : ViewModel() {
    private val reportInfo = MutableStateFlow(Report("", ""))
    private val onChat: (Chat) -> Unit = { chat ->
        _chatRoomDetail.value =
            chatRoomDetail.value.copy(chats = chatRoomDetail.value.chats.plus(chat))
    }
    private val chatRoomId = MutableStateFlow("")
    private val _chatRoomDetail =
        MutableStateFlow(ChatRoomDetail("", "농구할 사람", 8, 4, emptyList(), true))
    private val _chat = MutableStateFlow("")
    private val _uiEvent: MutableSharedFlow<UiEvent> = MutableSharedFlow()
    val chatRoomDetail = _chatRoomDetail.asStateFlow()
    val chat = _chat.asStateFlow()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(100)
            _chatRoomDetail.value = ChatRoomDetail("", "농구할 사람", 8, 4, dummyChats, true)
        }
    }

    fun initSocket(thunderId: String) {
        Timber.e("init chat socket")
        chatRoomId.value = thunderId
        socketHandler.connectSocket()
        socketHandler.subscribeChatRoom(chatRoomId.value)
        socketHandler.subscribeChat(onChat)
    }

    fun disconnectSocket() {
        Timber.e("Disconnect chat socket")
        socketHandler.unSubscribeChatRoom(chatRoomId.value)
        socketHandler.disconnectSocket()
    }

    fun sendChat() {
        socketHandler.sendChat(chatRoomId.value, chat.value)
        _chat.value = ""
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

    fun setAlarmState() {
        viewModelScope.launch {
            chatRepository.setChatRoomAlarm(
                thunderId = chatRoomId.value,
                isAlarm = !chatRoomDetail.value.isAlarm
            )
                .onSuccess {
                    _chatRoomDetail.value =
                        _chatRoomDetail.value.copy(isAlarm = !chatRoomDetail.value.isAlarm)
                }
                .onFailure { Timber.e("error is ${it.message}") }
        }
    }

    fun writeChat(chat: String) {
        _chat.value = chat
    }

    fun setReportInfo(thunderId: String, userId: String) {
        reportInfo.value = Report(thunderId, userId)
    }

    fun reportChat(reportIndex: Int) {
        viewModelScope.launch {
            chatRepository.reportChat(
                reportInfo.value.thunderId,
                reportInfo.value.userId,
                reportIndex
            )
                .onSuccess {
                    _uiEvent.emit(UiEvent.REPORT_SUCCESS)
                }
                .onFailure {
                    _uiEvent.emit(UiEvent.NETWORK_FAIL)
                }
        }
    }
}

data class Report(
    val thunderId: String,
    val userId: String
)
