package com.vershininds.mixture.sample.presentation.modules.catalog.router

import com.vershininds.mixture.router.MxtRouter
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogRouterContract
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogRouterContract.TypeRouterAction
import com.vershininds.mixture.sample.presentation.modules.details.DetailsModuleContract
import com.vershininds.mixture.view.AndroidComponent
import javax.inject.Inject


class CatalogRouter @Inject constructor(
        private val detailsModuleContract: DetailsModuleContract
) : CatalogRouterContract.Router {

    override var listener: MxtRouter.Listener? = null

    override fun actionHandler(androidComponent: AndroidComponent, action: TypeRouterAction) {
        when (action) {
            is TypeRouterAction.DetailsScreenAction -> showDetailsScreen(androidComponent, action.params)
        }
    }

    override fun showDetailsScreen(androidComponent: AndroidComponent, params: CatalogRouterContract.DetailsParams) {
        val activity = androidComponent.activity
        val intent = detailsModuleContract.createActivityIntent(activity, params.sampleObject)
        activity.startActivity(intent)
    }
}