package com.vershininds.mixture.sample.presentation.modules.main.di

import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.main.viewmodel.MainVm
import dagger.Module
import dagger.Provides

@Module
class MainPermDiModule {

    @PerFeatureScope
    @Provides
    fun providesDispatcher(): ActionDispatcher = ActionDispatcher()

    @PerFeatureScope
    @Provides
    fun provideViewModel(dispatcher: ActionDispatcher): MainVm = MainVm(dispatcher)
}