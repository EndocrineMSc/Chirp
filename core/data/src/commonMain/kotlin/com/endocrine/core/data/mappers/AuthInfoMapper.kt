package com.endocrine.core.data.mappers

import com.endocrine.core.data.dto.serializables.AuthInfoSerializable
import com.endocrine.core.data.dto.serializables.UserSerializable
import com.endocrine.core.domain.auth.AuthInfo
import com.endocrine.core.domain.auth.User

fun AuthInfoSerializable.toDomain(): AuthInfo {
    return AuthInfo(
        accessToken = accessToken,
        refreshToken = refreshToken,
        user = user.toDomain()
    )
}

fun UserSerializable.toDomain(): User {
    return User(
        id = id,
        email = email,
        userName = userName,
        hasVerifiedEmail = hasVerifiedEmail,
        profilePictureUrl = profilePictureUrl
    )
}