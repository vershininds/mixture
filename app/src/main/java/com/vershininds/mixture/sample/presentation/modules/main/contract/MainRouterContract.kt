package com.vershininds.mixture.sample.presentation.modules.main.contract

import com.vershininds.mixture.router.MvpRouter
import com.vershininds.mixture.view.AndroidComponent


interface MainRouterContract {

    interface Router : MvpRouter<MvpRouter.Listener> {
        fun showListScreen(androidComponent: AndroidComponent)
    }
}