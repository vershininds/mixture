package com.vershininds.mixture.sample.presentation.modules.main.di

import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.main.viewmodel.MainVm
import dagger.Component

@PerFeatureScope
@Component(modules = [MainPermDiModule::class])
interface MainPermComponent {
    fun getViewModel(): MainVm
    fun getDispatcher() : ActionDispatcher

    @Component.Builder
    interface Builder {
        fun build(): MainPermComponent
    }
}