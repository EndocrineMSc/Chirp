package com.endocrine.chat.data.chat

import com.endocrine.chat.data.mappers.toDomain
import com.endocrine.chat.data.mappers.toEntity
import com.endocrine.chat.data.mappers.toLastMessageView
import com.endocrine.chat.database.ChirpChatDatabase
import com.endocrine.chat.database.entities.ChatWithParticipants
import com.endocrine.chat.domain.chat.ChatRepository
import com.endocrine.chat.domain.chat.ChatService
import com.endocrine.chat.domain.models.Chat
import com.endocrine.core.domain.util.DataError
import com.endocrine.core.domain.util.Result
import com.endocrine.core.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineFirstChatRepository(
    private val chatService: ChatService,
    private val db: ChirpChatDatabase
) : ChatRepository {
    override fun getChats(): Flow<List<Chat>> {
        return db.chatDao.getChatsWithActiveParticipants()
            .map { chatWithParticipantsList ->
                chatWithParticipantsList.map { it.toDomain() }
            }
    }

    override suspend fun fetchChats(): Result<List<Chat>, DataError.Remote> {
        return chatService
            .getChats()
            .onSuccess { chats ->
                val chatsWithParticipants = chats.map { chat ->
                    ChatWithParticipants(
                        chat = chat.toEntity(),
                        participants = chat.participants.map { it.toEntity() },
                        lastMessage = chat.lastMessage?.toLastMessageView()
                    )
                }

                db.chatDao.upsertChatsWithParticipantsAndCrossRefs(
                    chats = chatsWithParticipants,
                    participantDao = db.chatParticipantDao,
                    crossRefDao = db.chatParticipantsCrossRefDao,
                    messageDao = db.chatMessageDao
                )
            }
    }
}