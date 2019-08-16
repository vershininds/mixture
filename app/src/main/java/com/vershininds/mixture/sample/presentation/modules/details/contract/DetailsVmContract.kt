package com.vershininds.mixture.sample.presentation.modules.details.contract

import com.vershininds.mixture.action.ViewAction
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.viewmodel.DataModel

interface DetailsVmContract {

    sealed class TypeViewAction : ViewAction() {
        class DataAction(val data: DataModel<SampleObject, String>) : TypeViewAction()
    }

    interface ViewModel {
        fun onClickFinish()
    }
}