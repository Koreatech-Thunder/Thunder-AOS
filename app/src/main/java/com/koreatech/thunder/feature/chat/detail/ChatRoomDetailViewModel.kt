package com.koreatech.thunder.feature.chat.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.ChatRoomDetail
import com.koreatech.thunder.domain.model.dummyChats
import com.koreatech.thunder.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChatRoomDetailViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {
    private val _chatRoomDetail = MutableStateFlow(ChatRoomDetail("농구할 사람", 8, 4, dummyChats, true))
    private val _chat = MutableStateFlow("")
    val chatRoomDetail = _chatRoomDetail.asStateFlow()
    val chat = _chat.asStateFlow()

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