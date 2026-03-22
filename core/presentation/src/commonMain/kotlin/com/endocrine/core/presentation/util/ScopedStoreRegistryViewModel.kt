package com.endocrine.core.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore

/**
 * A registry for [ViewModelStore] instances identified by a scope ID.
 *
 * This [ViewModel] manages the lifecycle of multiple [ViewModelStore]s that are intended
 * for scoped UI components (e.g. dialogs or bottom sheets). It ensures that all stores
 * are cleared when this registry is itself cleared.
 *
 * This registry is necessary because standard [ViewModel]s are typically tied to the
 * lifecycle of their parent [ViewModelStoreOwner]. When a UI component like a dialog
 * needs its own [ViewModel] that survives configuration changes while visible but
 * is cleared when dismissed, this registry provides a way to manage these independent
 * lifecycles within the parent's scope.
 */
class ScopedStoreRegistryViewModel : ViewModel() {

    private val stores = mutableMapOf<String, ViewModelStore>()

    fun getOrCreate(id: String): ViewModelStore = stores.getOrPut(id) { ViewModelStore() }

    fun clear(id: String) {
        stores
            .remove(id)
            ?.clear()
    }

    override fun onCleared() {
        super.onCleared()
        stores.values.forEach { it.clear() }
        stores.clear()
    }
}