package com.endocrine.chat.domain.chat

import com.endocrine.chat.domain.models.Chat
import com.endocrine.core.domain.util.DataError
import com.endocrine.core.domain.util.Result

interface ChatService {
    suspend fun createChat(
        otherUserIds: List<String>
    ): Result<Chat, DataError.Remote>

    suspend fun getChats(): Result<List<Chat>, DataError.Remote>
    suspend fun getChatById(chatId: String): Result<Chat, DataError.Remote>
}