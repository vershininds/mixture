package com.vershininds.mixture.sample.presentation.modules.details.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class DetailsHolderFactory(
        private val component: DetailsPermComponent
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsHolder(component) as T
    }
}