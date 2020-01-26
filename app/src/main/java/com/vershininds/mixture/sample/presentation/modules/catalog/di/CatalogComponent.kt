package com.vershininds.mixture.sample.presentation.modules.catalog.di

import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.router.MxtRouter2
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.catalog.view.CatalogFragment
import com.vershininds.mixture.sample.presentation.modules.catalog.viewmodel.CatalogVm
import dagger.BindsInstance
import dagger.Subcomponent

@PerFeatureScope
@Subcomponent(modules = [CatalogDiModule::class])
interface CatalogComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragment: CatalogFragment): CatalogComponent
    }

    fun getViewModel(): CatalogVm
    fun getDispatcher(): ActionDispatcher
    fun getRouter(): MxtRouter2

    fun inject(catalogFragment: CatalogFragment)
}