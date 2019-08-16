package com.vershininds.mixture.sample.presentation.modules.main.router

import com.vershininds.mixture.router.AbstractRouter
import com.vershininds.mixture.router.MvpRouter
import com.vershininds.mixture.sample.R
import com.vershininds.mixture.sample.presentation.modules.main.contract.MainRouterContract
import com.vershininds.mixture.sample.presentation.modules.catalog.CatalogModuleContract
import com.vershininds.mixture.view.AndroidComponent
import javax.inject.Inject

class MainRouter @Inject constructor(private val catalogModuleContract: CatalogModuleContract) :
        AbstractRouter<MvpRouter.Listener>(), MainRouterContract.Router {

    override fun showListScreen(androidComponent: AndroidComponent) {
        val fragmentManager = androidComponent.supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.container, catalogModuleContract.createFragment())
                .commit()
    }
}