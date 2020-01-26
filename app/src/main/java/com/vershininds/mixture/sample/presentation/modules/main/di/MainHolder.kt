package com.vershininds.mixture.sample.presentation.modules.main.di

import android.arch.lifecycle.ViewModel

class MainHolder(val component: MainPermComponent) : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        component.getViewModel().destroy()
    }
}