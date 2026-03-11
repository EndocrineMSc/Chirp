package com.endocrine.core.data.networking

import com.endocrine.core.domain.Result
import com.endocrine.core.domain.util.DataError
import io.ktor.client.engine.darwin.DarwinHttpRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.network.sockets.SocketTimeoutException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import platform.Foundation.NSURLErrorCallIsActive
import platform.Foundation.NSURLErrorCannotFindHost
import platform.Foundation.NSURLErrorDNSLookupFailed
import platform.Foundation.NSURLErrorDataNotAllowed
import platform.Foundation.NSURLErrorDomain
import platform.Foundation.NSURLErrorInternationalRoamingOff
import platform.Foundation.NSURLErrorNetworkConnectionLost
import platform.Foundation.NSURLErrorNotConnectedToInternet
import platform.Foundation.NSURLErrorResourceUnavailable
import platform.Foundation.NSURLErrorTimedOut
import kotlin.coroutines.coroutineContext

/**
 * iOS-specific implementation of [platformSafeCall].
 * Handles iOS-specific network exceptions like [DarwinHttpRequestException]
 * and common Ktor network exceptions.
 *
 * @param execute A suspending lambda that performs the actual network request and returns an [HttpResponse].
 * @param handleResponse A suspending lambda that processes the successful [HttpResponse] and maps it to a [Result].
 * @return A [Result] containing either the success value of type [T] or a [DataError.Remote].
 */
actual suspend fun <T> platformSafeCall(
    execute: suspend () -> HttpResponse,
    handleResponse: suspend (HttpResponse) -> Result<T, DataError.Remote>
): Result<T, DataError.Remote> {
    return try {
        val response = execute()
        handleResponse(response)
    } catch (e: DarwinHttpRequestException) {
        handleDarwinException(e)
    } catch (e: UnresolvedAddressException) {
        Result.Failure(DataError.Remote.NO_INTERNET)
    } catch (e: SocketTimeoutException) {
        Result.Failure(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: HttpRequestTimeoutException) {
        Result.Failure(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: SerializationException) {
        Result.Failure(DataError.Remote.SERIALIZATION)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        Result.Failure(DataError.Remote.UNKNOWN)
    }
}

private fun handleDarwinException(e: DarwinHttpRequestException): Result<Nothing, DataError.Remote> {
    val nsError = e.origin

    if (nsError.domain != NSURLErrorDomain) return Result.Failure(DataError.Remote.UNKNOWN)

    return when (nsError.code) {
        NSURLErrorNotConnectedToInternet,
        NSURLErrorNetworkConnectionLost,
        NSURLErrorCannotFindHost,
        NSURLErrorDNSLookupFailed,
        NSURLErrorResourceUnavailable,
        NSURLErrorInternationalRoamingOff,
        NSURLErrorCallIsActive,
        NSURLErrorDataNotAllowed -> {
            Result.Failure(DataError.Remote.NO_INTERNET)
        }

        NSURLErrorTimedOut -> Result.Failure(DataError.Remote.REQUEST_TIMEOUT)
        else -> Result.Failure(DataError.Remote.UNKNOWN)
    }
}