package com.endocrine.chat.data.chat

import com.endocrine.chat.data.dto.ChatDto
import com.endocrine.chat.data.dto.requests.CreateChatRequest
import com.endocrine.chat.data.mappers.toDomain
import com.endocrine.chat.domain.chat.ChatService
import com.endocrine.chat.domain.models.Chat
import com.endocrine.core.data.networking.get
import com.endocrine.core.data.networking.post
import com.endocrine.core.domain.util.DataError
import com.endocrine.core.domain.util.Result
import com.endocrine.core.domain.util.map
import io.ktor.client.HttpClient

class KtorChatService(
    private val httpClient: HttpClient
) : ChatService {
    override suspend fun createChat(otherUserIds: List<String>): Result<Chat, DataError.Remote> {
        return httpClient.post<CreateChatRequest, ChatDto>(
            route = "/chat",
            body = CreateChatRequest(otherUserIds)
        ).map { it.toDomain() }
    }

    override suspend fun getChats(): Result<List<Chat>, DataError.Remote> {
        return httpClient.get<List<ChatDto>>(
            route = "/chat"
        ).map { chatDtos ->
            chatDtos.map { it.toDomain() }
        }
    }

    override suspend fun getChatById(chatId: String): Result<Chat, DataError.Remote> {
        return httpClient.get<ChatDto>(
            route = "/chat/$chatId"
        ).map { it.toDomain() }
    }
}