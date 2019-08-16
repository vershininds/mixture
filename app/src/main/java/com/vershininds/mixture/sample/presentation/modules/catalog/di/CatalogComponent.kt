package com.vershininds.mixture.sample.presentation.modules.catalog.di

import com.vershininds.mixture.router.MxtRouter
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogInteractorContract
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogRouterContract
import com.vershininds.mixture.sample.presentation.modules.catalog.view.CatalogFragment
import dagger.Subcomponent

@PerFeatureScope
@Subcomponent(modules = [CatalogDiModule::class])
interface CatalogComponent {

    fun getInteractor() : CatalogInteractorContract.Interactor
    fun getRouter() : MxtRouter<MxtRouter.Listener, CatalogRouterContract.TypeRouterAction>
    fun inject(catalogFragment: CatalogFragment)
}