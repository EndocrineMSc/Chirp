package com.endocrine.chat.database.entities

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Room relation result class that embeds a [ChatMessageEntity] and resolves its sender as a
 * [ChatParticipantEntity] via the `senderId` → `userId` relationship.
 *
 * @property message The message entity.
 * @property sender The participant who sent this message.
 */
data class MessageWithSender(
    @Embedded
    val message: ChatMessageEntity,
    @Relation(
        parentColumn = "senderId",
        entityColumn = "userId"
    )
    val sender: ChatParticipantEntity
)
