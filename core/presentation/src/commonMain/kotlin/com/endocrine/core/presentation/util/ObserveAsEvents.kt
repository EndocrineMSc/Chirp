package com.endocrine.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * A Composable utility function used to observe a [Flow] as one-time events (e.g., navigation,
 * showing snackbars, or showing dialogs) in a lifecycle-aware manner.
 *
 * This function ensures that the events are only collected when the [Lifecycle] is at least
 * in the [Lifecycle.State.STARTED] state. It also uses [LaunchedEffect] to handle recomposition
 * and ensures that the [onEvent] callback is executed on the [Dispatchers.Main.immediate] to
 * allow for UI-related operations.
 *
 * @param T The type of the event.
 * @param flow The [Flow] of events to observe.
 * @param key1 An optional key to restart the [LaunchedEffect] when changed.
 * @param key2 An optional key to restart the [LaunchedEffect] when changed.
 * @param onEvent The callback invoked when an event is collected from the [flow].
 */
@Composable
fun <T> ObserveAsEvents(
    flow: Flow<T>,
    key1: Any? = null,
    key2: Any? = null,
    onEvent: suspend (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner, key1, key2) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect(onEvent)
            }
        }
    }
}