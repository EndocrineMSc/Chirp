package com.endocrine.chat.presentation.create_chat

import androidx.compose.foundation.text.input.TextFieldState
import com.endocrine.chat.domain.models.ChatParticipant

data class CreateChatState(
    val queryTextState: TextFieldState = TextFieldState(),
)