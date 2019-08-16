package com.vershininds.mixture.sample.presentation.modules.rx2interactor.di

import com.vershininds.mixture.router.MviRouter
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2InteractorContract
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2RouterContract
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.view.SampleRx2Fragment
import dagger.Subcomponent

@PerFeatureScope
@Subcomponent(modules = [SampleRx2DiModule::class])
interface SampleRx2Component {

    fun getInteractor() : SampleRx2InteractorContract.Interactor
    fun getRouter() : MviRouter<SampleRx2RouterContract.TypeRouterAction>
    fun inject(sampleRx2Fragment: SampleRx2Fragment)
}