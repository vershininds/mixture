package com.vershininds.mixture.sample.presentation.modules.main.router

import com.vershininds.mixture.action.RouterAction
import com.vershininds.mixture.router.MxtRouter
import com.vershininds.mixture.sample.R
import com.vershininds.mixture.sample.presentation.modules.catalog.CatalogModuleContract
import com.vershininds.mixture.sample.presentation.modules.main.contract.MainRouterContract
import com.vershininds.mixture.view.AndroidComponent
import javax.inject.Inject

class MainRouter @Inject constructor(private val catalogModuleContract: CatalogModuleContract) : MainRouterContract.Router {

    override var listener: MxtRouter.Listener? = null

    override fun actionHandler(androidComponent: AndroidComponent, action: RouterAction) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showListScreen(androidComponent: AndroidComponent) {
        val fragmentManager = androidComponent.supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.container, catalogModuleContract.createFragment())
                .commit()
    }
}