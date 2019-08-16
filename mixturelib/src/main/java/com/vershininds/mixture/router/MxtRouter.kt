package com.vershininds.mixture.router

import com.vershininds.mixture.action.RouterAction
import com.vershininds.mixture.view.AndroidComponent

interface MxtRouter<L : MxtRouter.Listener, T : RouterAction> {

    interface Listener

    /**
     * [Listener] target class who get operation result from interactor
     */
    var listener: L?

    fun destroy() {
        listener = null
    }

    fun actionHandler(androidComponent: AndroidComponent, action: T)
}