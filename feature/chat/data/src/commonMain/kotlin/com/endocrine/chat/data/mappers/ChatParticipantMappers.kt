package com.endocrine.chat.data.mappers

import com.endocrine.chat.data.dto.ChatParticipantDto
import com.endocrine.chat.database.entities.ChatParticipantEntity
import com.endocrine.chat.domain.models.ChatParticipant

fun ChatParticipantDto.toDomain(): ChatParticipant {
    return ChatParticipant(
        userId = userId,
        userName = username,
        profilePictureUrl = profilePictureUrl
    )
}

fun ChatParticipantEntity.toDomain(): ChatParticipant {
    return ChatParticipant(
        userId = userId,
        userName = userName,
        profilePictureUrl = profilePictureUrl
    )
}

fun ChatParticipant.toEntity(): ChatParticipantEntity {
    return ChatParticipantEntity(
        userId = userId,
        userName = userName,
        profilePictureUrl = profilePictureUrl
    )
}