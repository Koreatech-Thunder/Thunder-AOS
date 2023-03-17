package com.koreatech.thunder.feature.chat.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.ChatRoom
import com.koreatech.thunder.domain.model.dummyChatRooms
import com.koreatech.thunder.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChatRoomViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {
    private val _chatRooms: MutableStateFlow<List<ChatRoom>> = MutableStateFlow(dummyChatRooms)
    val chatRooms = _chatRooms.asStateFlow()

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
