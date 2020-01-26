package com.vershininds.mixture.sample.presentation.modules.details.di

import android.arch.lifecycle.ViewModel

class DetailsHolder(val component: DetailsPermComponent) : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        component.getViewModel().destroy()
    }
}