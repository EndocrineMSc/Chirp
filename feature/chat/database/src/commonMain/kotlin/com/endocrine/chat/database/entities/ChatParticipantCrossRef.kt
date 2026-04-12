package com.endocrine.chat.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * Room join-table entity linking [ChatEntity] and [ChatParticipantEntity] in a many-to-many
 * relationship.
 *
 * The composite primary key `(chatId, userId)` prevents duplicate entries. Both foreign keys
 * cascade on delete, so cross-refs are cleaned up automatically when either side is removed.
 *
 * @property chatId ID of the chat.
 * @property userId ID of the participant.
 * @property isActive Whether this participant is currently an active member of the chat.
 */
@Entity(
    primaryKeys = ["chatId", "userId"],
    foreignKeys = [
        ForeignKey(
            entity = ChatEntity::class,
            parentColumns = ["chatId"],
            childColumns = ["chatId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ChatParticipantEntity::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["chatId"]),
        Index(value = ["userId"])
    ]
)
data class ChatParticipantCrossRef(
    val chatId: String,
    val userId: String,
    val isActive: Boolean
)
