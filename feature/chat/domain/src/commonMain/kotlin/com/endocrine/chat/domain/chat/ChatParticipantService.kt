package com.endocrine.chat.domain.chat

import com.endocrine.chat.domain.models.ChatParticipant
import com.endocrine.core.domain.util.DataError
import com.endocrine.core.domain.util.Result

interface ChatParticipantService {
    suspend fun searchParticipant(
        query: String
    ): Result<ChatParticipant, DataError.Remote>
}