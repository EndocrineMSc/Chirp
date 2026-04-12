package com.endocrine.chat.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room database entity representing a user who participates in at least one chat.
 *
 * Participants are stored independently of chats. The many-to-many relationship between
 * chats and participants is maintained by [ChatParticipantCrossRef].
 *
 * @property userId Unique identifier for this user.
 * @property userName Display name of the user.
 * @property profilePictureUrl URL of the user's profile picture.
 */
@Entity
data class ChatParticipantEntity(
    @PrimaryKey
    val userId: String,
    val userName: String,
    val profilePictureUrl: String?,
)
