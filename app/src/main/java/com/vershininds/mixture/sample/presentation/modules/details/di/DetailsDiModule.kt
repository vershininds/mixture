package com.vershininds.mixture.sample.presentation.modules.details.di

import com.vershininds.mixture.router.MviRouter
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsInteractorContract
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsRouterContract
import com.vershininds.mixture.sample.presentation.modules.details.interactor.DetailsInteractor
import com.vershininds.mixture.sample.presentation.modules.details.router.DetailsRouter
import dagger.Module
import dagger.Provides

@Module
class DetailsDiModule {
    @PerFeatureScope
    @Provides
    fun providesInteractor(interactor: DetailsInteractor): DetailsInteractorContract.Interactor = interactor

    @PerFeatureScope
    @Provides
    fun providesRouter(router: DetailsRouter): MviRouter<DetailsRouterContract.TypeRouterAction> = router
}