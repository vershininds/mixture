package com.vershininds.mixture.sample.presentation.modules.main.contract

import com.vershininds.mixture.action.UserAction
import com.vershininds.mixture.action.ViewAction
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.viewmodel.DataModel

interface MainVmContract {

    sealed class TypeViewAction : ViewAction() {
        class DataAction(val data: DataModel<SampleObject, String>) : TypeViewAction()
    }
}