package com.endocrine.core.data.auth

import com.endocrine.core.data.dto.requests.EmailRequest
import com.endocrine.core.data.dto.requests.LoginRequest
import com.endocrine.core.data.dto.requests.RegisterRequest
import com.endocrine.core.data.dto.serializables.AuthInfoSerializable
import com.endocrine.core.data.mappers.toDomain
import com.endocrine.core.data.networking.get
import com.endocrine.core.data.networking.post
import com.endocrine.core.domain.auth.AuthInfo
import com.endocrine.core.domain.auth.AuthService
import com.endocrine.core.domain.util.DataError
import com.endocrine.core.domain.util.EmptyResult
import com.endocrine.core.domain.util.Result
import com.endocrine.core.domain.util.map
import io.ktor.client.HttpClient

class KtorAuthService(
    private val httpClient: HttpClient
) : AuthService {
    override suspend fun login(
        email: String,
        password: String
    ): Result<AuthInfo, DataError.Remote> {
        return httpClient
            .post<LoginRequest, AuthInfoSerializable>(
                route = "/auth/login",
                body = LoginRequest(
                    email = email,
                    password = password
                )
            )
            .map { authInfoSerializable ->
                authInfoSerializable.toDomain()
            }
    }


    override suspend fun register(
        email: String,
        userName: String,
        password: String
    ): EmptyResult<DataError.Remote> {
        return httpClient.post(
            route = "/auth/register",
            body = RegisterRequest(
                email = email,
                username = userName,
                password = password
            )
        )
    }

    override suspend fun resendVerificationEmail(email: String): EmptyResult<DataError.Remote> {
        return httpClient.post(
            route = "/auth/resend-verification",
            body = EmailRequest(email)
        )
    }

    override suspend fun verifyEmail(token: String): EmptyResult<DataError.Remote> {
        return httpClient.get(
            route = "/auth/verify",
            queryParams = mapOf("token" to token)
        )
    }
}