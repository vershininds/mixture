package com.vershininds.mixture.sample.presentation.modules.main.di

import android.arch.lifecycle.ViewModelProviders
import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.router.MxtRouter2
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.catalog.CatalogModuleContract
import com.vershininds.mixture.sample.presentation.modules.main.router.MainRouter
import com.vershininds.mixture.sample.presentation.modules.main.view.MainActivity
import com.vershininds.mixture.sample.presentation.modules.main.viewmodel.MainVm
import dagger.Module
import dagger.Provides

@Module
class MainDiModule {

    @PerFeatureScope
    @Provides
    fun providesViewModel(mainPermComponent: MainPermComponent): MainVm {
        return mainPermComponent.getViewModel()
    }

    @PerFeatureScope
    @Provides
    fun providesDispatcher(mainPermComponent: MainPermComponent): ActionDispatcher {
        return mainPermComponent.getDispatcher()
    }

    @PerFeatureScope
    @Provides
    fun providesRouter(
            activity: MainActivity,
            dispatcher: ActionDispatcher,
            catalogModuleContract: CatalogModuleContract
    ): MxtRouter2 =
            MainRouter(activity, dispatcher, catalogModuleContract)


    @PerFeatureScope
    @Provides
    fun providesPermComponent(
            activity: MainActivity
    ): MainPermComponent {
        val diPermComponent = DaggerMainPermComponent.builder()
                .build()

        return ViewModelProviders
                .of(activity, MainHolderFactory(diPermComponent))
                .get(MainHolder::class.java)
                .component
    }
}