package com.vershininds.mixture.sample.presentation.modules.details.contract

import com.vershininds.mixture.action.RouterAction
import com.vershininds.mixture.router.MxtRouter
import com.vershininds.mixture.view.AndroidComponent

interface DetailsRouterContract {

    sealed class TypeRouterAction : RouterAction() {
        class FinishScreenAction : TypeRouterAction()
    }

    interface Router : MxtRouter<MxtRouter.Listener, TypeRouterAction> {
        /**
         * @param androidComponent [AndroidComponent]
         */
        fun finishScreen(androidComponent: AndroidComponent)
    }
}