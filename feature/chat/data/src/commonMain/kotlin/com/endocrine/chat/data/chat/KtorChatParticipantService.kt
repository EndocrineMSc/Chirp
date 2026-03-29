package com.endocrine.chat.data.chat

import com.endocrine.chat.data.dto.ChatParticipantDto
import com.endocrine.chat.data.mappers.toDomain
import com.endocrine.chat.domain.chat.ChatParticipantService
import com.endocrine.chat.domain.models.ChatParticipant
import com.endocrine.core.data.networking.get
import com.endocrine.core.domain.util.DataError
import com.endocrine.core.domain.util.Result
import com.endocrine.core.domain.util.map
import io.ktor.client.HttpClient


class KtorChatParticipantService(
    private val httpClient: HttpClient
) : ChatParticipantService {
    override suspend fun searchParticipant(query: String): Result<ChatParticipant, DataError.Remote> {
        return httpClient.get<ChatParticipantDto>(
            route = "/participants",
            queryParams = mapOf(
                "query" to query
            )
        ).map { it.toDomain() }
    }
}