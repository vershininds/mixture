package com.vershininds.mixture.sample.presentation.modules.details.viewmodel

import android.os.Bundle
import android.os.Parcelable
import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.action.UserAction
import com.vershininds.mixture.action.handle
import com.vershininds.mixture.dispatcher.subscribeVm
import com.vershininds.mixture.interactor.MxtInteractor
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsRouterContract.TypeRouterAction
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsVmContract
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsVmContract.TypeViewAction
import com.vershininds.mixture.viewmodel.BaseVm
import com.vershininds.mixture.viewmodel.DataModel
import javax.inject.Inject

class DetailsVm @Inject constructor(
        private val dispatcher: ActionDispatcher,
        private val interactor: MxtInteractor,
        private val sampleObject: SampleObject
) : BaseVm(dispatcher) {

    private var viewData : DetailsViewData

    init {
        viewData = DetailsViewData(sampleObject)
    }

    override fun subscribeOnDispatcher() {
        dispatcher.subscribeVm("DetailsVm",
                { action: UserAction -> action.handle { handleUserAction(it) } }
        )
    }

    override fun destroy() {
        interactor.destroy()
        super.destroy()
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

    private fun handleUserAction(action: UserAction) {
        when (action) {
            is DetailsVmContract.TypeUserAction.ClickOnFinishAction -> action.handle {
                postActionDispatcher(TypeRouterAction.FinishScreenAction())
            }
        }
    }
}