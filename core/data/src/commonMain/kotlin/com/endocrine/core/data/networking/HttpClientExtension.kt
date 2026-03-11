package com.endocrine.core.data.networking

import com.endocrine.core.domain.Result
import com.endocrine.core.domain.util.DataError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

/**
 * Executes a network call in a platform-safe way, handling platform-specific network exceptions
 * and converting them into [DataError.Remote] failures.
 *
 * @param execute A suspending lambda that performs the actual network request and returns an [HttpResponse].
 * @param handleResponse A suspending lambda that processes the successful [HttpResponse] and maps it to a [Result].
 * @return A [Result] containing either the success value of type [T] or a [DataError.Remote].
 */
expect suspend fun <T> platformSafeCall(
    execute: suspend () -> HttpResponse,
    handleResponse: suspend (HttpResponse) -> Result<T, DataError.Remote>
): Result<T, DataError.Remote>

/**
 * A convenience wrapper around [platformSafeCall] that uses [responseToResult] to handle the response.
 *
 * @param execute A suspending lambda that performs the actual network request and returns an [HttpResponse].
 * @return A [Result] containing either the success value of type [T] or a [DataError.Remote].
 */
suspend inline fun <reified T> safeCall(
    noinline execute: suspend() -> HttpResponse
): Result<T, DataError.Remote> {
    return platformSafeCall(
        execute = execute
    ) { response ->
        responseToResult(response)
    }
}

/**
 * Converts an [HttpResponse] to a [Result] based on the HTTP status code.
 *
 * Successful responses (2xx) are mapped to [Result.Success] with the body parsed as [T].
 * Common error status codes are mapped to specific [DataError.Remote] failures.
 *
 * @param response The [HttpResponse] to convert.
 * @return A [Result] containing either the success value of type [T] or a [DataError.Remote].
 */
suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, DataError.Remote> {
    return when(response.status.value) {
        in 200 .. 299 -> {
            try {
                Result.Success(response.body<T>())
            } catch(_: NoTransformationFoundException) {
                Result.Failure(DataError.Remote.SERIALIZATION)
            }
        }
        400 -> Result.Failure(DataError.Remote.BAD_REQUEST)
        401 -> Result.Failure(DataError.Remote.UNAUTHORIZED)
        403 -> Result.Failure(DataError.Remote.FORBIDDEN)
        404 -> Result.Failure(DataError.Remote.NOT_FOUND)
        408 -> Result.Failure(DataError.Remote.REQUEST_TIMEOUT)
        409 -> Result.Failure(DataError.Remote.CONFLICT)
        413 -> Result.Failure(DataError.Remote.PAYLOAD_TOO_LARGE)
        429 -> Result.Failure(DataError.Remote.TOO_MANY_REQUEST)
        500 -> Result.Failure(DataError.Remote.SERVER_ERROR)
        503 -> Result.Failure(DataError.Remote.SERVICE_UNAVAILABLE)
        else -> Result.Failure(DataError.Remote.UNKNOWN)
    }
}

/**
 * Constructs a full URL for a given [route] by prepending the base URL if it's not already present.
 */
fun constructRoute(route: String): String {
    return when {
        route.contains(UrlConstants.BASE_URL_HTTP) -> route
        route.startsWith("/") -> "${UrlConstants.BASE_URL_HTTP}$route"
        else -> "${UrlConstants.BASE_URL_HTTP}/$route"
    }
}