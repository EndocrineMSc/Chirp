package com.endocrine.core.designsystem.components.avatar

data class ChatParticipantUi(
    val id: String,
    val userName: String,
    val initials: String,
    val imageUrl: String? = null,
)
