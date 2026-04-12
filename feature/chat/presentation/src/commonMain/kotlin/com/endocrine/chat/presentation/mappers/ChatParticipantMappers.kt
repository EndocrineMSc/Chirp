package com.endocrine.chat.presentation.mappers

import com.endocrine.chat.domain.models.ChatParticipant
import com.endocrine.core.designsystem.components.avatar.ChatParticipantUi
import com.endocrine.core.domain.auth.User

fun ChatParticipant.toUi(): ChatParticipantUi {
    return ChatParticipantUi(
        id = userId,
        userName = userName,
        initials = initials,
        imageUrl = profilePictureUrl
    )
}

fun User.toUi(): ChatParticipantUi {
    return ChatParticipantUi(
        id = id,
        userName = userName,
        initials = userName.take(2).uppercase(),
        imageUrl = profilePictureUrl
    )
}