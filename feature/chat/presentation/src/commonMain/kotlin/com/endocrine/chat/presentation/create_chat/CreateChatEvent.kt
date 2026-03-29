package com.endocrine.chat.presentation.create_chat

import com.endocrine.chat.domain.models.Chat

sealed interface CreateChatEvent {
    data class OnChatCreated(val chat: Chat): CreateChatEvent
}