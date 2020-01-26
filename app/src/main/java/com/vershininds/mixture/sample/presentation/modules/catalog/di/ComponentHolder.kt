package com.vershininds.mixture.sample.presentation.modules.catalog.di

import android.arch.lifecycle.ViewModel

class ComponentHolder(val component: CatalogPermComponent) : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        component.getViewModel().destroy()
    }
}