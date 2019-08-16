package com.vershininds.mixture.sample.presentation.modules.details.viewmodel

import android.os.Bundle
import android.os.Parcelable
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsInteractorContract
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsRouterContract.TypeRouterAction
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsVmContract
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsVmContract.TypeViewAction
import com.vershininds.mixture.viewmodel.BaseVm
import com.vershininds.mixture.viewmodel.DataModel
import javax.inject.Inject

class DetailsVm @Inject constructor(
        private val interactor: DetailsInteractorContract.Interactor,
        private val sampleObject: SampleObject
) : BaseVm(), DetailsVmContract.ViewModel, DetailsInteractorContract.Presenter {

    private var viewData : DetailsViewData

    init {
        viewData = DetailsViewData(sampleObject)
        interactor.setListener(this)
    }

    override fun onCleared() {
        interactor.setListener(null)
        interactor.destroy()

        super.onCleared()
    }

    override fun loadData() {
        postActionDispatcher(TypeViewAction.DataAction(DataModel(data = viewData.sampleObject, state = DataModel.State.DATA)))
    }

    public override fun saveInstanceState(outState: Bundle): Bundle {
        outState.putParcelable(KEY_DATA, viewData)
        return outState
    }

    public override fun restoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) loadData()
        else {
            val data = savedInstanceState.getParcelable<Parcelable>(KEY_DATA) as DetailsViewData?
            data?.let {
                viewData = it
                postActionDispatcher(TypeViewAction.DataAction(DataModel(data = it.sampleObject, state = DataModel.State.DATA)))
            }
        }
    }

    override fun onClickFinish() {
        postActionDispatcher(TypeRouterAction.FinishScreenAction())
    }
}