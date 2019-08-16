package com.vershininds.mixture.sample.presentation.modules.rx2interactor.router

import com.vershininds.mixture.router.AbstractRouter
import com.vershininds.mixture.router.MvpRouter
import com.vershininds.mixture.sample.presentation.modules.details.DetailsModuleContract
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2RouterContract
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2RouterContract.TypeRouterAction
import com.vershininds.mixture.view.AndroidComponent
import javax.inject.Inject


class SampleRx2Router @Inject constructor(private val detailsModuleContract: DetailsModuleContract) :
        AbstractRouter<MvpRouter.Listener>(), SampleRx2RouterContract.Router {

    override fun actionHandler(androidComponent: AndroidComponent, action: TypeRouterAction) {
        when (action) {
            is TypeRouterAction.DetailsScreenAction -> showDetailsScreen(androidComponent, action.params)
        }
    }

    override fun showDetailsScreen(androidComponent: AndroidComponent, params: SampleRx2RouterContract.DetailsParams) {
        val activity = androidComponent.activity
        val intent = detailsModuleContract.createActivityIntent(activity, params.sampleObject)
        activity.startActivity(intent)
    }
}