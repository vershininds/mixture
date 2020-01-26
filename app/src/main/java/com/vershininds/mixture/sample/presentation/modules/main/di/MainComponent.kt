package com.vershininds.mixture.sample.presentation.modules.main.di

import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.router.MxtRouter2
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.main.view.MainActivity
import com.vershininds.mixture.sample.presentation.modules.main.viewmodel.MainVm
import dagger.BindsInstance
import dagger.Subcomponent

@PerFeatureScope
@Subcomponent(modules = [MainDiModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: MainActivity): MainComponent
    }

    fun getViewModel(): MainVm
    fun getDispatcher(): ActionDispatcher
    fun getRouter(): MxtRouter2

    fun inject(mainActivity: MainActivity)
}