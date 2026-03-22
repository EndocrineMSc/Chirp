package com.endocrine.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import org.koin.compose.viewmodel.koinViewModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Provides a scoped [ViewModelStoreOwner] for UI components like dialogs or bottom sheets.
 *
 * When [visible] is true, it creates and provides a [ViewModelStoreOwner] through
 * [LocalViewModelStoreOwner]. The store is cleared and removed from the [ScopedStoreRegistryViewModel]
 * when [visible] becomes false.
 *
 * This utility is necessary to allow [ViewModel]s inside a dialog or bottom sheet to
 * persist during configuration changes while the component is visible, yet ensure they
 * are properly cleaned up when the component is dismissed. It prevents these [ViewModel]s
 * from being tied to the longer lifecycle of the parent screen.
 */
@OptIn(ExperimentalUuidApi::class)
@Composable
fun DialogSheetScopedViewModel(
    visible: Boolean,
    scopeId: String = rememberSaveable {
        Uuid
            .random()
            .toString()
    },
    content: @Composable () -> Unit
) {
    val parentOwner = LocalViewModelStoreOwner.current
        ?: throw IllegalStateException("No parent owner found")

    val registry = koinViewModel<ScopedStoreRegistryViewModel>(
        viewModelStoreOwner = parentOwner
    )

    var owner by remember { mutableStateOf<ViewModelStoreOwner?>(null) }

    LaunchedEffect(
        visible,
        scopeId
    ) {
        if (visible && owner == null) {
            owner = object : ViewModelStoreOwner {
                override val viewModelStore: ViewModelStore
                    get() = registry.getOrCreate(scopeId)
            }
        } else if (!visible && owner != null) {
            registry.clear(scopeId)
            owner = null
        }
    }

    owner?.let { dialogOwner ->
        CompositionLocalProvider(LocalViewModelStoreOwner provides dialogOwner) {
            content()
        }
    }
}
