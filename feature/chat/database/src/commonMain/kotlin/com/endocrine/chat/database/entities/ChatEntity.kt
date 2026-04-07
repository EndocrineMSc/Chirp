package com.endocrine.chat.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room database entity representing a chat conversation.
 *
 * @property chatId Unique identifier for the chat.
 * @property lastMessage Preview text of the most recent message, or null if the chat is empty.
 * @property lastActivityAt Unix timestamp (ms) of the last activity in this chat, used for sorting.
 */
@Entity
data class ChatEntity(
    @PrimaryKey
    val chatId: String,
    val lastMessage: String?,
    val lastActivityAt: Long
)
