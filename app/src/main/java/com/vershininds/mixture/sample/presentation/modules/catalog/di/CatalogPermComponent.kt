package com.vershininds.mixture.sample.presentation.modules.catalog.di

import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.sample.domain.services.ServiceMaker
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.catalog.viewmodel.CatalogVm
import dagger.BindsInstance
import dagger.Component

@PerFeatureScope
@Component(modules = [CatalogPermDiModule::class])
interface CatalogPermComponent {
    fun getViewModel(): CatalogVm
    fun getDispatcher() : ActionDispatcher

    @Component.Builder
    interface Builder {
        fun build(): CatalogPermComponent
        @BindsInstance
        fun serviceManager(serviceMaker: ServiceMaker): Builder
    }
}