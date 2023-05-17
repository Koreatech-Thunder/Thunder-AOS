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
        Timber.e("hanbun connect socket")
    }

    fun disconnectSocket() {
        socket.disconnect()
        Timber.e("hanbun disconnect socket")
    }

    fun sendChat(thunderId: String, message: String) {
        val newChat: String = NewChat(thunderId = thunderId, message = message)
            .let { chat -> gson.toJson(chat) }
        socket.emit("sendMessage", newChat)
    }

    fun subscribeChatRooms(onChat: (Chat) -> Unit) {
        socket.emit("subscribeChatRoom")

        val listener = Listener { data ->
            val chat: Chat = gson.fromJson(data[0].toString(), Chat::class.java)
            Timber.e("hanbun chatId : ${chat}")
            onChat(chat)
        }
        Timber.e("hanbun subscribe chat room")

        socket.on("newChat", listener)
    }

    fun unSubscribeChatRooms() {
        socket.emit("unsubscribeChatRoom")
        Timber.e("hanbun unsubscribe chat room")
        socket.off("newChat")
    }

    fun subscribeChatRoom(thunderId: String, onChat: (Chat) -> Unit) {
        socket.emit("subscribeChat", thunderId)

        val listener = Listener { data ->
            val chat: Chat = gson.fromJson(data[0].toString(), Chat::class.java)
            Timber.e("hanbun chatId : ${chat}")
            onChat(chat)
        }
        Timber.e("hanbun subscribe chat detail")

        socket.on("newChat", listener)
    }

    fun unSubscribeChatRoom(thunderId: String) {
        socket.emit("unsubscribeChat", thunderId)
        Timber.e("hanbun unsubscribe chat detail")
        socket.off("newChat")
    }
}
