package com.vershininds.mixture.sample.presentation.modules.catalog.viewmodel

import android.os.Bundle
import android.os.Parcelable
import com.vershininds.mixture.action.*
import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.dispatcher.subscribeVm
import com.vershininds.mixture.interactor.MxtInteractor
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogInteractorContract
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogInteractorContract.TypeInteractorAction
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogRouterContract.TypeRouterAction
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogVmContract
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogVmContract.TypeViewAction
import com.vershininds.mixture.viewmodel.BaseVm
import com.vershininds.mixture.viewmodel.DataModel
import com.vershininds.mixture.viewmodel.ViewState

class CatalogVm constructor(
        private val dispatcher: ActionDispatcher,
        private val interactor: MxtInteractor
) : BaseVm(dispatcher) {

    private var viewData : CatalogViewData

    init {
        viewData = CatalogViewData()
        subscribeOnDispatcher()
    }

    override fun subscribeOnDispatcher() {
        dispatcher.subscribeVm("CatalogVm",
                { action: UserAction -> handleUserAction(action) },
                { action: InteractorAction -> handleInteractorBackAction(action) }
        )
    }

    override fun destroy() {
        interactor.destroy()
        super.destroy()
    }

    override fun loadData() {
        viewData.viewState = ViewState.LOADING
        postActionDispatcher(TypeViewAction.DataAction(DataModel(state = DataModel.State.LOADING)))
        postActionDispatcher(TypeInteractorAction.ObtainDataAction())
    }

    public override fun saveInstanceState(outState: Bundle): Bundle {
        outState.putParcelable(KEY_DATA, viewData)
        return outState
    }

    public override fun restoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) loadData()
        else {
            val data = savedInstanceState.getParcelable<Parcelable>(KEY_DATA) as CatalogViewData?
            data?.let {
                viewData = it
                when (viewData.viewState) {
                    ViewState.LOADING -> postActionDispatcher(TypeViewAction.DataAction(DataModel(state = DataModel.State.LOADING)))
                    ViewState.ERROR -> postActionDispatcher(TypeViewAction.DataAction(DataModel(error = viewData.error, state = DataModel.State.ERROR)))
                    else -> postActionDispatcher(TypeViewAction.DataAction(DataModel(data = it.sampleObjectList, state = DataModel.State.DATA)))
                }
            }
        }
    }

    private fun clickOnItem(sampleObject: SampleObject) {
        postActionDispatcher(TypeRouterAction.DetailsScreenAction(sampleObject))
    }

    private fun retry() {
        loadData()
    }

    private fun handleUserAction(action: UserAction) {
        when (action) {
            is CatalogVmContract.TypeUserAction.ClickOnItemAction -> action.handle { clickOnItem(it.sampleObject) }
            is CatalogVmContract.TypeUserAction.LoadErrorListAction -> action.handle { loadErrorData() }
            is CatalogVmContract.TypeUserAction.LoadEmptyListAction -> action.handle { loadEmptyData() }
            is CatalogVmContract.TypeUserAction.RetryAction -> action.handle { retry() }
        }
    }

    private fun handleInteractorBackAction(action: InteractorAction) {
        when (action) {
            is CatalogInteractorContract.TypeInteractorBackAction.ObtainedDataAction -> action.handle {
                onObtainedData(it.data, it.throwable)
            }
        }
    }

    private fun onObtainedData(data: List<SampleObject>, throwable: Throwable?) {
        if (throwable == null) {
            viewData.viewState = ViewState.DATA
            viewData.sampleObjectList = data
            postActionDispatcher(TypeViewAction.DataAction(DataModel(data = data, state = DataModel.State.DATA)))
        } else {
            viewData.viewState = ViewState.ERROR
            viewData.error = throwable.message
            postActionDispatcher(TypeViewAction.DataAction(DataModel(error = viewData.error, state = DataModel.State.ERROR)))
        }
    }

    private fun loadErrorData(){
        viewData.viewState = ViewState.LOADING
        postActionDispatcher(TypeViewAction.DataAction(DataModel(state = DataModel.State.LOADING)))
        postActionDispatcher(TypeInteractorAction.ObtainErrorDataAction())
    }

    private fun loadEmptyData(){
        viewData.viewState = ViewState.LOADING
        postActionDispatcher(TypeViewAction.DataAction(DataModel(state = DataModel.State.LOADING)))
        postActionDispatcher(TypeInteractorAction.ObtainEmptyDataAction())
    }
}