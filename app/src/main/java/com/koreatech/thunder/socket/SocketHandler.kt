package com.koreatech.thunder.socket

import com.google.gson.Gson
import com.koreatech.thunder.domain.model.Chat
import com.koreatech.thunder.socket.model.NewChat
import io.socket.client.Socket
import io.socket.emitter.Emitter.Listener
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SocketHandler @Inject constructor(
    private val socket: Socket,
    private val gson: Gson
) {
    fun connectSocket() {
        socket.connect()
        Timber.e("soccket connect ${socket.connected()}")
    }

    fun disConnectSocket() {
        socket.disconnect()
    }

    fun sendChat(thunderId: String, message: String) {
        val newChat: String = NewChat(thunderId = thunderId, message = message)
            .let { chat -> gson.toJson(chat) }
        socket.emit("sendMessage", newChat)
    }

    fun subscribeChat(onChat: (Chat) -> Unit) {
        val listener = Listener { data ->
            val chat: Chat = gson.fromJson(data[0].toString(), Chat::class.java)
            onChat(chat)
        }
        socket.on("newChat", listener)
    }

    fun subscribeChatRooms() {
        socket.emit("subscribeChatRoom")
    }

    fun unSubscribeChatRooms() {
        socket.emit("unsubscribeChatRoom")
    }

    fun subscribeChatRoom(thunderId: String) {
        socket.emit("subscribeChat", thunderId)
    }

    fun unSubscribeChatRoom(thunderId: String) {
        socket.emit("unsubscribeChat", thunderId)
    }
}
