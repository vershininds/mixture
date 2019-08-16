package com.vershininds.mixture.sample.presentation.modules.main.di

import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.main.view.MainActivity
import dagger.Subcomponent

@PerFeatureScope
@Subcomponent(modules = [MainDiModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}