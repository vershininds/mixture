package com.vershininds.mixture.sample.presentation.modules.details.di

import com.vershininds.mixture.router.MxtRouter
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsInteractorContract
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsRouterContract
import com.vershininds.mixture.sample.presentation.modules.details.view.DetailsActivity
import dagger.Subcomponent

@PerFeatureScope
@Subcomponent(modules = [DetailsDiModule::class])
interface DetailsComponent {

    fun getInteractor() : DetailsInteractorContract.Interactor
    fun getRouter() : MxtRouter<MxtRouter.Listener, DetailsRouterContract.TypeRouterAction>
    fun inject(detailsActivity: DetailsActivity)
}