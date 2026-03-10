package com.endocrine.core.domain

/**
 * Represents the result of an operation that can either succeed with data of type [D]
 * or fail with an error of type [E].
 * This allows for type safe successes, errors and easy utility function extensions.
 *
 * @param D The type of data returned on success.
 * @param E The type of error returned on failure, must implement [Error].
 */
sealed interface Result<out D, out E: Error> {
    /**
     * Represents a successful result containing [data].
     */
    data class Success<out D>(val data: D): Result<D, Nothing>

    /**
     * Represents a failed result containing an [error].
     */
    data class Failure<out E: Error>(val error: E): Result<Nothing, E>
}

/**
 * Marker interface for error types used in [Result].
 */
interface Error

/**
 * Maps the data of a [Result.Success] using the provided [map] function.
 * If the result is a [Result.Failure], it is returned as is.
 *
 * Example:
 * ```kotlin
 * val result: Result<Int, MyError> = Result.Success(5)
 * val mapped = result.map { it * 2 } // Result.Success(10)
 * ```
 */
inline fun <T, E: Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when (this) {
        is Result.Failure -> Result.Failure(error)
        is Result.Success -> Result.Success(map(this.data))
    }
}

/**
 * Executes the provided [action] if the result is a [Result.Success].
 * Returns the [Result] unchanged.
 *
 * Example:
 * ```kotlin
 * val result: Result<String, MyError> = Result.Success("Hello")
 * result.onSuccess { println(it) } // prints "Hello"
 * ```
 */
inline fun <T, E: Error> Result<T, E>.onSuccess(action: (T) -> Unit): Result<T, E> {
    return when (this) {
        is Result.Failure -> this
        is Result.Success -> {
            action(this.data)
            this
        }
    }
}

/**
 * Executes the provided [action] if the result is a [Result.Failure].
 * Returns the [Result] unchanged.
 *
 * Example:
 * ```kotlin
 * val result: Result<String, MyError> = Result.Failure(MyError.BAD_REQUEST)
 * result.onFailure { println(it) } // prints BAD_REQUEST
 * ```
 */
inline fun <T, E: Error> Result<T, E>.onFailure(action: (E) -> Unit): Result<T, E> {
    return when (this) {
        is Result.Success -> this
        is Result.Failure -> {
            action(this.error)
            this
        }
    }
}

/**
 * Maps a [Result] to an [EmptyResult], replacing the success data with [Unit].
 *
 * Example:
 * ```kotlin
 * val result: Result<User, MyError> = Result.Success(User("1", "John"))
 * val empty = result.asEmptyResult() // Result.Success(Unit)
 * ```
 */
fun <T, E: Error> Result<T, E>.asEmptyResult(): EmptyResult<E> {
    return map {}
}

typealias EmptyResult<E> = Result<Unit, E>
