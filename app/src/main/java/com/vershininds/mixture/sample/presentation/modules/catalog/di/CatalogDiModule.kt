package com.vershininds.mixture.sample.presentation.modules.catalog.di

import com.vershininds.mixture.router.MviRouter
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogInteractorContract
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogRouterContract
import com.vershininds.mixture.sample.presentation.modules.catalog.interactor.CatalogInteractor
import com.vershininds.mixture.sample.presentation.modules.catalog.router.CatalogRouter
import dagger.Module
import dagger.Provides

@Module
class CatalogDiModule {
    @PerFeatureScope
    @Provides
    fun providesInteractor(interactor: CatalogInteractor): CatalogInteractorContract.Interactor = interactor

    @PerFeatureScope
    @Provides
    fun providesRouter(router: CatalogRouter): MviRouter<CatalogRouterContract.TypeRouterAction> = router
}