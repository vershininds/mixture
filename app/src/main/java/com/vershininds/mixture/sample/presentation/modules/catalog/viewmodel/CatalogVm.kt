package com.vershininds.mixture.sample.presentation.modules.catalog.viewmodel

import android.os.Bundle
import android.os.Parcelable
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogInteractorContract
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogRouterContract
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogRouterContract.TypeRouterAction
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogVmContract
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogVmContract.TypeViewAction
import com.vershininds.mixture.viewmodel.BaseVm
import com.vershininds.mixture.viewmodel.DataModel
import javax.inject.Inject

class CatalogVm @Inject constructor(
        private val interactor: CatalogInteractorContract.Interactor
) : BaseVm(), CatalogVmContract.ViewModel, CatalogInteractorContract.Presenter {

    private var viewData : CatalogViewData

    init {
        viewData = CatalogViewData()
        interactor.setListener(this)
    }

    override fun onCleared() {
        interactor.setListener(null)
        interactor.destroy()

        super.onCleared()
    }

    override fun loadData() {
        postActionDispatcher(TypeViewAction.DataAction(DataModel(state = DataModel.State.LOADING)))
        interactor.obtainData()
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
                postActionDispatcher(TypeViewAction.DataAction(DataModel(data = it.sampleObjectList, state = DataModel.State.DATA)))
            }
        }
    }


    override fun clickOnItem(sampleObject: SampleObject) {
        postActionDispatcher(TypeRouterAction.DetailsScreenAction(
                CatalogRouterContract.DetailsParams(sampleObject))
        )
    }

    override fun retry() {
        loadData()
    }

    override fun onObtainedData(data: List<SampleObject>, throwable: Throwable?) {
        if (throwable == null) {
            data.let {
                viewData.sampleObjectList = it
                postActionDispatcher(TypeViewAction.DataAction(DataModel(data = it, state = DataModel.State.DATA)))
            }
        } else {
            viewData.error = throwable.message
            postActionDispatcher(TypeViewAction.DataAction(DataModel(error = viewData.error, state = DataModel.State.ERROR)))
        }
    }
}