package com.vershininds.mixture.router

import com.vershininds.mixture.action.RouterAction
import com.vershininds.mixture.view.AndroidComponent

interface MviRouter<T : RouterAction> {
    fun actionHandler(androidComponent: AndroidComponent, action: T)
}