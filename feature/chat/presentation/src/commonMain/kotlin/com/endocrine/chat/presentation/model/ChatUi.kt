package com.endocrine.chat.presentation.model

import com.endocrine.chat.domain.models.ChatMessage
import com.endocrine.core.designsystem.components.avatar.ChatParticipantUi

data class ChatUi(
    val id: String,
    val localParticipant: ChatParticipantUi,
    val otherParticipants: List<ChatParticipantUi>,
    val lastMessage: ChatMessage?,
    val lastMessageSenderUsername: String?
)
