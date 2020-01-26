package com.vershininds.mixture.sample.presentation.modules.catalog.di

import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.interactor.MxtInteractor
import com.vershininds.mixture.sample.domain.services.ServiceMaker
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.catalog.interactor.CatalogInteractor
import com.vershininds.mixture.sample.presentation.modules.catalog.viewmodel.CatalogVm
import dagger.Module
import dagger.Provides

@Module
class CatalogPermDiModule {

    @PerFeatureScope
    @Provides
    fun providesInteractor(dispatcher: ActionDispatcher, serviceMaker: ServiceMaker): MxtInteractor {
        return CatalogInteractor(dispatcher, serviceMaker)
    }

    @PerFeatureScope
    @Provides
    fun providesDispatcher(): ActionDispatcher = ActionDispatcher()

    @PerFeatureScope
    @Provides
    fun provideViewModel(dispatcher: ActionDispatcher, interactor: MxtInteractor): CatalogVm {
        return CatalogVm(dispatcher, interactor)
    }
}