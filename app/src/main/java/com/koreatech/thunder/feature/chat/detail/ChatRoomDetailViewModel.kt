package com.koreatech.thunder.feature.chat.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.ChatRoomDetail
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
    private val _chatRoomDetail = MutableStateFlow(ChatRoomDetail("", 0, 0, emptyList(), true))
    val chatRoomDetail = _chatRoomDetail.asStateFlow()

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
}
