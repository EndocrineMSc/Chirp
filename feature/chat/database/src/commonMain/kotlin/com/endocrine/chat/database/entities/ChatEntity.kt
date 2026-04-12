package com.endocrine.chat.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room database entity representing a chat conversation.
 *
 * @property chatId Unique identifier for the chat.
 * @property lastActivityAt Unix timestamp (ms) of the last activity in this chat, used for sorting.
 */
@Entity
data class ChatEntity(
    @PrimaryKey
    val chatId: String,
    val lastActivityAt: Long
)
