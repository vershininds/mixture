package com.vershininds.mixture.sample.presentation.modules.catalog.contract;


import com.vershininds.mixture.action.RouterAction
import com.vershininds.mixture.router.MxtRouter
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogRouterContract.Router
import com.vershininds.mixture.view.AndroidComponent


/**
 * Contract between [ViewModel] and [Router]
 */

interface CatalogRouterContract {

    /**
     * It's only sample how post more than one parameter in router
     *
     * @param sampleObject some data object
     */
    data class DetailsParams(val sampleObject: SampleObject)

    sealed class TypeRouterAction : RouterAction() {
        class DetailsScreenAction(val params: DetailsParams) : TypeRouterAction()
    }

    interface Router : MxtRouter<MxtRouter.Listener, TypeRouterAction> {
        /**
         * @param androidComponent [AndroidComponent]
         */
        fun showDetailsScreen(androidComponent: AndroidComponent, params: DetailsParams)
    }
}