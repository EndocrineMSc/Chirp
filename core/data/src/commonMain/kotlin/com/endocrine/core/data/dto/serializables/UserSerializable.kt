package com.endocrine.core.data.dto.serializables

import kotlinx.serialization.Serializable

@Serializable
data class UserSerializable(
    val id: String,
    val email: String,
    val userName: String,
    val hasVerifiedEmail: Boolean,
    val profilePictureUrl: String? = null,
)