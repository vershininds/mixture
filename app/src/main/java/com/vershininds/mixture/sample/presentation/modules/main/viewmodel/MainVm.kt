package com.vershininds.mixture.sample.presentation.modules.main.viewmodel

import android.os.Bundle
import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.dispatcher.subscribeVm
import com.vershininds.mixture.sample.presentation.modules.main.contract.MainRouterContract
import com.vershininds.mixture.viewmodel.BaseVm
import javax.inject.Inject

class MainVm @Inject constructor(
        private val dispatcher: ActionDispatcher
) : BaseVm(dispatcher) {


    override fun subscribeOnDispatcher() {
        dispatcher.subscribeVm("DetailsVm") {}
    }

    override fun loadData() {
        postActionDispatcher(MainRouterContract.TypeRouterAction.ShowCatalogScreenAction())
    }

    public override fun restoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) loadData()
    }
}