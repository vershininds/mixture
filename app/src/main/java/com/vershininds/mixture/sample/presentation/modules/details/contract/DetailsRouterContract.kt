package com.vershininds.mixture.sample.presentation.modules.details.contract

import com.vershininds.mixture.action.RouterAction

interface DetailsRouterContract {

    sealed class TypeRouterAction : RouterAction() {
        class FinishScreenAction : TypeRouterAction()
    }
}