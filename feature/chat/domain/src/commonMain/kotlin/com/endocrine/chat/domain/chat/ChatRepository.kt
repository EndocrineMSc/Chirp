package com.endocrine.chat.domain.chat

import com.endocrine.chat.domain.models.Chat
import com.endocrine.core.domain.util.DataError
import com.endocrine.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getChats(): Flow<List<Chat>>
    suspend fun fetchChats(): Result<List<Chat>, DataError.Remote>
}