package com.endocrine.chat.presentation.mappers

import com.endocrine.chat.domain.models.ChatParticipant
import com.endocrine.core.designsystem.components.avatar.ChatParticipantUi

fun ChatParticipant.toUi(): ChatParticipantUi {
    return ChatParticipantUi(
        id = userId,
        userName = userName,
        initials = initials,
        imageUrl = profilePictureUrl
    )
}