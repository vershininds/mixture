package com.vershininds.mixture.sample.presentation.modules.catalog.contract;


import com.vershininds.mixture.action.RouterAction
import com.vershininds.mixture.sample.data.SampleObject

/**
 * Contract between [ViewModel] and [Router]
 */

interface CatalogRouterContract {

    sealed class TypeRouterAction : RouterAction() {
        class DetailsScreenAction(val sampleObject: SampleObject) : TypeRouterAction()
    }
}