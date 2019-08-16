package com.vershininds.mixture.sample.presentation.modules.rx2interactor.viewmodel

import android.os.Bundle
import android.os.Parcelable
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2InteractorContract
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2RouterContract
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2RouterContract.TypeRouterAction
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2VmContract
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2VmContract.TypeViewAction
import com.vershininds.mixture.viewmodel.BaseVm
import com.vershininds.mixture.viewmodel.DataModel
import javax.inject.Inject

class SampleRx2Vm @Inject constructor(
        private val interactor: SampleRx2InteractorContract.Interactor
) : BaseVm(), SampleRx2VmContract.ViewModel, SampleRx2InteractorContract.Presenter {

    private var viewData : SampleRx2ViewData

    init {
        viewData = SampleRx2ViewData()
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
            val data = savedInstanceState.getParcelable<Parcelable>(KEY_DATA) as SampleRx2ViewData?
            data?.let {
                viewData = it
                postActionDispatcher(TypeViewAction.DataAction(DataModel(data = it.sampleObjectList, state = DataModel.State.DATA)))
            }
        }
    }


    override fun clickOnItem(sampleObject: SampleObject) {
        postActionDispatcher(TypeRouterAction.DetailsScreenAction(
                SampleRx2RouterContract.DetailsParams(sampleObject))
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