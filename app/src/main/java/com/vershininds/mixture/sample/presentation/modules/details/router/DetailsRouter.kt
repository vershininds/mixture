package com.vershininds.mixture.sample.presentation.modules.details.router

import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.action.RouterAction
import com.vershininds.mixture.action.handle
import com.vershininds.mixture.dispatcher.subscribeRouter
import com.vershininds.mixture.router.MxtRouter2
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsRouterContract
import com.vershininds.mixture.view.AndroidComponent
import javax.inject.Inject

class DetailsRouter @Inject constructor(
        private val androidComponent: AndroidComponent,
        private val dispatcher: ActionDispatcher
) : MxtRouter2 {

    private val tag = DetailsRouter::class.java.simpleName

    override fun subscribeOnDispatcher() {
        dispatcher.subscribeRouter(tag) { action: RouterAction ->
            when (action) {
                is DetailsRouterContract.TypeRouterAction.FinishScreenAction -> action.handle { finishScreen(androidComponent) }
            }
        }
    }

    override fun unsubscribeDispatcher() {
        dispatcher.removeSubscriber(tag)
    }

    private fun finishScreen(androidComponent: AndroidComponent) {
        androidComponent.activity.finish()
    }
}