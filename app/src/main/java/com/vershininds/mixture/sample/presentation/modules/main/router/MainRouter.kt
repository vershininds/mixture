package com.vershininds.mixture.sample.presentation.modules.main.router

import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.action.RouterAction
import com.vershininds.mixture.action.handle
import com.vershininds.mixture.dispatcher.subscribeRouter
import com.vershininds.mixture.router.MxtRouter2
import com.vershininds.mixture.sample.R
import com.vershininds.mixture.sample.presentation.modules.catalog.CatalogModuleContract
import com.vershininds.mixture.sample.presentation.modules.main.contract.MainRouterContract
import com.vershininds.mixture.view.AndroidComponent
import javax.inject.Inject

class MainRouter @Inject constructor(
        private val androidComponent: AndroidComponent,
        private val dispatcher: ActionDispatcher,
        private val catalogModuleContract: CatalogModuleContract
) : MxtRouter2 {

    private val tag = MainRouter::class.java.simpleName

    override fun subscribeOnDispatcher() {
        dispatcher.subscribeRouter(tag) { action: RouterAction ->
            when (action) {
                is MainRouterContract.TypeRouterAction.ShowCatalogScreenAction -> action.handle { showCatalogScreen(androidComponent) }
            }
        }
    }

    override fun unsubscribeDispatcher() {
        dispatcher.removeSubscriber(tag)
    }

    private fun showCatalogScreen(androidComponent: AndroidComponent) {
        val fragmentManager = androidComponent.supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.container, catalogModuleContract.createFragment())
                .commit()
    }
}