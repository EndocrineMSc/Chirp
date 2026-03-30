package com.endocrine.chat.presentation.chat_list

import com.endocrine.chat.domain.models.ChatParticipant
import com.endocrine.chat.presentation.model.ChatUi
import com.endocrine.core.designsystem.components.avatar.ChatParticipantUi
import com.endocrine.core.presentation.util.UiText

data class ChatListState(
    val chats: List<ChatUi> = emptyList(),
    val error: UiText? = null,
    val localParticipant: ChatParticipantUi? = null,
    val isUserMenuOpen: Boolean = false,
    val showLogoutConfirmation: Boolean = false,
    val selectedChatId: String? = null,
    val isLoading: Boolean = false,
)