package com.endocrine.core.data.auth

import com.endocrine.core.data.dto.requests.EmailRequest
import com.endocrine.core.data.dto.requests.RegisterRequest
import com.endocrine.core.data.networking.post
import com.endocrine.core.domain.auth.AuthService
import com.endocrine.core.domain.util.DataError
import com.endocrine.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class KtorAuthService(
    private val httpClient: HttpClient
) : AuthService {
    override suspend fun register(
        email: String, userName: String, password: String
    ): EmptyResult<DataError.Remote> {
        return httpClient.post(
            route = "/auth/register", body = RegisterRequest(
                email = email, username = userName, password = password
            )
        )
    }

    override suspend fun resendVerificationEmail(email: String): EmptyResult<DataError.Remote> {
        return httpClient.post(
            route = "/auth/resend-verification", body = EmailRequest(email)
        )
    }
}