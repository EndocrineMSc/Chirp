package com.endocrine.chat.presentation.mappers

import com.endocrine.chat.domain.models.Chat
import com.endocrine.chat.presentation.model.ChatUi

fun Chat.toUi(localParticipantId: String): ChatUi {
    val (local, other) = participants.partition { it.userId == localParticipantId }

    return ChatUi(
        id = id,
        localParticipant = local.first().toUi(),
        otherParticipants = other.map { it.toUi() },
        lastMessage = lastMessage,
        lastMessageSenderUsername = lastMessage?.let {
            participants.first { it.userId == lastMessage?.senderId }.userName
        }
    )
}