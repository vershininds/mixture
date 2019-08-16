package com.vershininds.mixture.sample.presentation.modules.details.router

import com.vershininds.mixture.router.MxtRouter
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsRouterContract
import com.vershininds.mixture.view.AndroidComponent
import javax.inject.Inject

class DetailsRouter @Inject constructor() : DetailsRouterContract.Router {

    override var listener: MxtRouter.Listener? = null

    override fun actionHandler(androidComponent: AndroidComponent, action: DetailsRouterContract.TypeRouterAction) {
        when (action) {
            is DetailsRouterContract.TypeRouterAction.FinishScreenAction -> finishScreen(androidComponent)
        }
    }

    override fun finishScreen(androidComponent: AndroidComponent) {
        androidComponent.activity.finish()
    }
}