package com.vershininds.mixture.sample.presentation.modules.main.router

import com.vershininds.mixture.router.AbstractRouter
import com.vershininds.mixture.router.MvpRouter
import com.vershininds.mixture.sample.R
import com.vershininds.mixture.sample.presentation.modules.main.contract.MainRouterContract
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.SampleRx2ModuleContract
import com.vershininds.mixture.view.AndroidComponent
import javax.inject.Inject

class MainRouter @Inject constructor(private val sampleRx2ModuleContract: SampleRx2ModuleContract) :
        AbstractRouter<MvpRouter.Listener>(), MainRouterContract.Router {

    override fun showListScreen(androidComponent: AndroidComponent) {
        val fragmentManager = androidComponent.supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.container, sampleRx2ModuleContract.createFragment())
                .commit()
    }
}