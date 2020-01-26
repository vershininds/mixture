package com.vershininds.mixture.sample.presentation.modules.catalog.router

import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.action.RouterAction
import com.vershininds.mixture.action.handle
import com.vershininds.mixture.dispatcher.subscribeRouter
import com.vershininds.mixture.router.MxtRouter2
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogRouterContract.TypeRouterAction
import com.vershininds.mixture.sample.presentation.modules.details.DetailsModuleContract
import com.vershininds.mixture.view.AndroidComponent


class CatalogRouter constructor(
        private val androidComponent: AndroidComponent,
        private val dispatcher: ActionDispatcher,
        private val detailsModuleContract: DetailsModuleContract
) : MxtRouter2 {

    private val tag = CatalogRouter::class.java.simpleName

    override fun subscribeOnDispatcher() {
        dispatcher.subscribeRouter(tag) { action: RouterAction ->
            when (action) {
                is TypeRouterAction.DetailsScreenAction -> action.handle { showDetailsScreen(androidComponent, it.sampleObject) }
            }
        }
    }

    override fun unsubscribeDispatcher() {
        dispatcher.removeSubscriber(tag)
    }

    private fun showDetailsScreen(androidComponent: AndroidComponent, params: SampleObject) {
        val activity = androidComponent.activity
        val intent = detailsModuleContract.createActivityIntent(activity, params)
        activity.startActivity(intent)
    }
}