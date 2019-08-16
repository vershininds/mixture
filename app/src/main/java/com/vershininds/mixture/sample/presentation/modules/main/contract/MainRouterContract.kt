package com.vershininds.mixture.sample.presentation.modules.main.contract

import com.vershininds.mixture.action.RouterAction
import com.vershininds.mixture.router.MxtRouter
import com.vershininds.mixture.view.AndroidComponent


interface MainRouterContract {

    interface Router : MxtRouter<MxtRouter.Listener, RouterAction> {
        fun showListScreen(androidComponent: AndroidComponent)
    }
}