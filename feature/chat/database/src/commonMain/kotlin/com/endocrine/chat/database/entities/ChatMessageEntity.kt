package com.endocrine.chat.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Room database entity representing a single message within a chat.
 *
 * Uses a [ForeignKey] on [ChatEntity.chatId] with CASCADE delete, so all messages are
 * automatically removed when their parent chat is deleted.
 *
 * @property messageId Unique identifier for this message.
 * @property chatId The ID of the chat this message belongs to.
 * @property senderId The ID of the user who sent the message.
 * @property content The text content of the message.
 * @property timestamp Unix timestamp (ms) of when the message was sent.
 * @property deliveryStatus String representation of the message's delivery status (see [com.endocrine.chat.domain.models.ChatMessageDeliveryStatus]).
 * @property deliveryStatusTimestamp Unix timestamp (ms) of the last delivery status change; defaults to [timestamp].
 */
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ChatEntity::class,
            parentColumns = ["chatId"],
            childColumns = ["chatId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["chatId"]),
        Index(value = ["timestamp"])
    ]
)
data class ChatMessageEntity(
    @PrimaryKey
    val messageId: String,
    val chatId: String,
    val senderId: String,
    val content: String,
    val timestamp: Long,
    val deliveryStatus: String,
    val deliveryStatusTimestamp: Long = timestamp
)
