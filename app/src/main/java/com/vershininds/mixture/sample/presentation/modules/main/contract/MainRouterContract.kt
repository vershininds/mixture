package com.vershininds.mixture.sample.presentation.modules.main.contract

import com.vershininds.mixture.action.RouterAction


interface MainRouterContract {

    sealed class TypeRouterAction : RouterAction() {
        class ShowCatalogScreenAction() : TypeRouterAction()
    }
}