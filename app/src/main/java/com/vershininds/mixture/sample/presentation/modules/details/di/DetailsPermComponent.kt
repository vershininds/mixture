package com.vershininds.mixture.sample.presentation.modules.details.di

import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.domain.services.ServiceMaker
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.details.viewmodel.DetailsVm
import dagger.BindsInstance
import dagger.Component

@PerFeatureScope
@Component(modules = [DetailsPermDiModule::class])
interface DetailsPermComponent {
    fun getViewModel(): DetailsVm
    fun getDispatcher() : ActionDispatcher

    @Component.Builder
    interface Builder {
        fun build(): DetailsPermComponent
        @BindsInstance
        fun serviceManager(serviceMaker: ServiceMaker): Builder
        @BindsInstance
        fun data(sampleObject: SampleObject): Builder
    }
}