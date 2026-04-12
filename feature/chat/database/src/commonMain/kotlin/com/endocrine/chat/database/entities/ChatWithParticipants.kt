package com.endocrine.chat.database.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.endocrine.chat.database.view.LastMessageView

/**
 * Room relation result class combining a [ChatEntity] with its list of [ChatParticipantEntity]
 * members, resolved via the [ChatParticipantCrossRef] junction table.
 *
 * Use this when you only need chat + participant data without message history.
 *
 * @property chat The chat entity.
 * @property participants All participants currently associated with this chat.
 */
data class ChatWithParticipants(
    @Embedded
    val chat: ChatEntity,
    @Relation(
        parentColumn = "chatId",
        entityColumn = "userId",
        associateBy = Junction(ChatParticipantCrossRef::class)
    )
    val participants: List<ChatParticipantEntity>,
    @Relation(
        parentColumn = "chatId",
        entityColumn = "chatId",
        entity = LastMessageView::class
    )
    val lastMessage: LastMessageView?
)

/**
 * Extended Room relation result class that includes a [ChatEntity], its [ChatParticipantEntity]
 * list, and all [MessageWithSender] entries for the chat.
 *
 * Use this when you need the full picture of a chat in a single query, e.g. when rendering the
 * chat detail screen.
 *
 * @property chat The chat entity.
 * @property participants All participants associated with this chat.
 * @property messageWithSenders All messages in the chat, each paired with their sender.
 */
data class ChatInfoEntity(
    @Embedded
    val chat: ChatEntity,
    @Relation(
        parentColumn = "chatId",
        entityColumn = "userId",
        associateBy = Junction(ChatParticipantCrossRef::class)
    )
    val participants: List<ChatParticipantEntity>,
    @Relation(
        parentColumn = "chatId",
        entityColumn = "chatId",
        entity = ChatMessageEntity::class
    )
    val messageWithSenders: List<MessageWithSender>
)
