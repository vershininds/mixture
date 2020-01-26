package com.vershininds.mixture.sample.presentation.modules.catalog.di

import android.arch.lifecycle.ViewModelProviders
import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.router.MxtRouter2
import com.vershininds.mixture.sample.domain.services.ServiceMaker
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.catalog.router.CatalogRouter
import com.vershininds.mixture.sample.presentation.modules.catalog.view.CatalogFragment
import com.vershininds.mixture.sample.presentation.modules.catalog.viewmodel.CatalogVm
import com.vershininds.mixture.sample.presentation.modules.details.DetailsModuleContract
import dagger.Module
import dagger.Provides

@Module
class CatalogDiModule {

    @PerFeatureScope
    @Provides
    fun providesViewModel(catalogPermComponent: CatalogPermComponent): CatalogVm {
        return catalogPermComponent.getViewModel()
    }

    @PerFeatureScope
    @Provides
    fun providesDispatcher(catalogPermComponent: CatalogPermComponent): ActionDispatcher {
        return catalogPermComponent.getDispatcher()
    }

    @PerFeatureScope
    @Provides
    fun providesRouter(fragment: CatalogFragment, dispatcher: ActionDispatcher, detailsModuleContract: DetailsModuleContract): MxtRouter2 =
            CatalogRouter(fragment, dispatcher, detailsModuleContract)


    @PerFeatureScope
    @Provides
    fun providesPermComponent(fragment: CatalogFragment, serviceMaker: ServiceMaker): CatalogPermComponent {
        val diPermComponent = DaggerCatalogPermComponent.builder()
                .serviceManager(serviceMaker)
                .build()

        return ViewModelProviders
                .of(fragment, ComponentHolderFactory(diPermComponent))
                .get(ComponentHolder::class.java)
                .component
    }
}