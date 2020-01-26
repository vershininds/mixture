package com.vershininds.mixture.sample.presentation.modules.details.di

import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.interactor.MxtInteractor
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.domain.services.ServiceMaker
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.details.interactor.DetailsInteractor
import com.vershininds.mixture.sample.presentation.modules.details.viewmodel.DetailsVm
import dagger.Module
import dagger.Provides

@Module
class DetailsPermDiModule {

    @PerFeatureScope
    @Provides
    fun providesInteractor(dispatcher: ActionDispatcher, serviceMaker: ServiceMaker): MxtInteractor {
        return DetailsInteractor(dispatcher, serviceMaker)
    }

    @PerFeatureScope
    @Provides
    fun providesDispatcher(): ActionDispatcher = ActionDispatcher()

    @PerFeatureScope
    @Provides
    fun provideViewModel(
            dispatcher: ActionDispatcher,
            interactor: MxtInteractor,
            sampleObject: SampleObject
    ): DetailsVm {
        return DetailsVm(dispatcher, interactor, sampleObject)
    }
}