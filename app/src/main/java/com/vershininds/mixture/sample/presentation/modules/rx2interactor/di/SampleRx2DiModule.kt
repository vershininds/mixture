package com.vershininds.mixture.sample.presentation.modules.rx2interactor.di

import com.vershininds.mixture.router.MviRouter
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2InteractorContract
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2RouterContract
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.interactor.SampleRx2Interactor
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.router.SampleRx2Router
import dagger.Module
import dagger.Provides

@Module
class SampleRx2DiModule {
    @PerFeatureScope
    @Provides
    fun providesInteractor(interactor: SampleRx2Interactor): SampleRx2InteractorContract.Interactor = interactor

    @PerFeatureScope
    @Provides
    fun providesRouter(router: SampleRx2Router): MviRouter<SampleRx2RouterContract.TypeRouterAction> = router
}