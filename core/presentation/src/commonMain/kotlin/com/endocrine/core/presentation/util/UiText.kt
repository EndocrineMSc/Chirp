package com.endocrine.core.presentation.util

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

/**
 * A sealed interface representing UI-related text that can either be a literal string 
 * or a string resource with optional arguments.
 *
 * This enables using StringResources efficiently outside a UI context.
 */
sealed interface UiText {
    data class DynamicString(val value: String): UiText

    /**
     * Represents a string resource with optional arguments.
     *
     * @property id The [StringResource] to be used.
     * @property args Optional arguments to be passed to the string resource for formatting.
     */
    class Resource(
        val id: StringResource,
        val args: Array<Any> = arrayOf()
    ): UiText

    /**
     * Resolves the [UiText] into a [String] within a Composable context.
     * 
     * WARNING: This should only be used in the UI layer and not in ViewModels, 
     * as resolving strings in a ViewModel can prevent them from updating when 
     * the system language changes at runtime.
     */
    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is Resource -> stringResource(
                resource = id,
                *args
            )
        }
    }

    /**
     * Resolves the [UiText] into a [String] asynchronously.
     * 
     * WARNING: This should only be used in the UI layer and not in ViewModels, 
     * as resolving strings in a ViewModel can prevent them from updating when 
     * the system language changes at runtime.
     */
    suspend fun asStringAsync(): String {
        return when (this) {
            is DynamicString -> value
            is Resource -> getString(
                resource = id,
                *args
            )
        }
    }
}