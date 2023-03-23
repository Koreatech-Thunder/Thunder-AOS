package com.koreatech.thunder.socket

import com.google.gson.Gson
import com.koreatech.thunder.data.model.response.ChatResponse
import com.koreatech.thunder.socket.model.NewChat
import io.socket.client.Socket
import io.socket.emitter.Emitter.Listener
import javax.inject.Inject

class SocketHandler @Inject constructor(
    private val socket: Socket,
    private val gson: Gson
) {
    fun connectSocket() {
        socket.connect()
    }

    fun disConnectSocket() {
        socket.disconnect()
    }

    fun sendChat(thunderId: String, message: String) {
        val newChat: String = NewChat(thunderId = thunderId, message = message)
            .let { chat -> gson.toJson(chat) }
        socket.emit("sendMessage", newChat)
    }

    fun subscribeChat(onChat: (ChatResponse) -> Unit) {
        val listener = Listener { data ->
            val chat: ChatResponse = gson.fromJson(data[0].toString(), ChatResponse::class.java)
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
