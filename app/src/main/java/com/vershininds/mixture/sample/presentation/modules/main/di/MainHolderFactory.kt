package com.vershininds.mixture.sample.presentation.modules.main.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MainHolderFactory(
        private val component: MainPermComponent
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainHolder(component) as T
    }
}