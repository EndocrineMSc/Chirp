package com.endocrine.core.domain.auth

import kotlinx.coroutines.flow.Flow

/**
 * An interface for managing and observing the authentication session.
 */
interface SessionStorage {
    /**
     * Observes the current authentication info.
     *
     * @return A [Flow] that emits the current [AuthInfo], or `null` if the user is not authenticated.
     */
    fun observeAuthInfo(): Flow<AuthInfo?>

    /**
     * Sets the current authentication info.
     *
     * @param info The new [AuthInfo] to be stored, or `null` to clear the session.
     */
    suspend fun set(info: AuthInfo?)
}