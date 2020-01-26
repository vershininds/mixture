package com.vershininds.mixture.sample.presentation.modules.catalog.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ComponentHolderFactory(
        private val component: CatalogPermComponent
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ComponentHolder(component) as T
    }
}