package com.vershininds.mixture.sample.presentation.modules.catalog.contract

import com.vershininds.mixture.action.UserAction
import com.vershininds.mixture.action.ViewAction
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.viewmodel.DataModel

interface CatalogVmContract {

    sealed class TypeViewAction : ViewAction() {
        class DataAction(val data: DataModel<List<SampleObject>, String>) : TypeViewAction()
        //TODO: create sample show dialog
//        class ErrorDialogWithCustomMessageAction(val errorMsg: String) : TypeViewAction()
    }

    sealed class TypeUserAction : UserAction() {
        class ClickOnItemAction(val sampleObject: SampleObject) : TypeUserAction()
        class LoadErrorListAction : TypeUserAction()
        class LoadEmptyListAction : TypeUserAction()
        class RetryAction : TypeUserAction()
        //TODO: create sample show dialog
//        fun positiveClickErrorDialog()
    }
}