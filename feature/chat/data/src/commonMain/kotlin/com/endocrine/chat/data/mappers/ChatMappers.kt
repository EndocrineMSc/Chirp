package com.endocrine.chat.data.mappers

import com.endocrine.chat.data.dto.ChatDto
import com.endocrine.chat.database.entities.ChatEntity
import com.endocrine.chat.database.entities.ChatInfoEntity
import com.endocrine.chat.database.entities.ChatWithParticipants
import com.endocrine.chat.database.entities.MessageWithSender
import com.endocrine.chat.domain.models.Chat
import com.endocrine.chat.domain.models.ChatInfo
import com.endocrine.chat.domain.models.ChatMessage
import com.endocrine.chat.domain.models.ChatMessageDeliveryStatus
import com.endocrine.chat.domain.models.ChatParticipant
import kotlin.time.Instant

typealias DataMessageWithSender = MessageWithSender
typealias DomainMessageWithSender = com.endocrine.chat.domain.models.MessageWithSender

fun ChatDto.toDomain(): Chat {
    return Chat(
        id = id,
        participants = participants.map { it.toDomain() },
        lastActivityAt = Instant.parse(lastActivityAt),
        lastMessage = lastMessage?.toDomain()
    )
}

fun ChatEntity.toDomain(
    participants: List<ChatParticipant>,
    lastMessage: ChatMessage? = null
): Chat {
    return Chat(
        id = chatId,
        participants = participants,
        lastActivityAt = Instant.fromEpochMilliseconds(lastActivityAt),
        lastMessage = lastMessage
    )
}

fun ChatWithParticipants.toDomain(): Chat {
    return Chat(
        id = chat.chatId,
        participants = participants.map { it.toDomain() },
        lastActivityAt = Instant.fromEpochMilliseconds(chat.lastActivityAt),
        lastMessage = lastMessage?.toDomain(),
    )
}

fun Chat.toEntity(): ChatEntity {
    return ChatEntity(
        chatId = id,
        lastActivityAt = lastActivityAt.toEpochMilliseconds()
    )
}

fun DataMessageWithSender.toDomain(): DomainMessageWithSender {
    return DomainMessageWithSender(
        message = message.toDomain(),
        sender = sender.toDomain(),
        deliveryStatus = ChatMessageDeliveryStatus.valueOf(message.deliveryStatus)
    )
}

fun ChatInfoEntity.toDomain(): ChatInfo {
    return ChatInfo(
        chat = chat.toDomain(
            this.participants.map { it.toDomain() }
        ),
        messages = messageWithSenders.map { it.toDomain() }
    )
}