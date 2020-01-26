package com.vershininds.mixture.sample.presentation.modules.catalog.contract

import com.vershininds.mixture.action.InteractorAction
import com.vershininds.mixture.sample.data.SampleObject

interface CatalogInteractorContract {

    sealed class TypeInteractorAction : InteractorAction() {
        class ObtainDataAction : TypeInteractorAction()
        class ObtainErrorDataAction : TypeInteractorAction()
        class ObtainEmptyDataAction : TypeInteractorAction()
    }

    sealed class TypeInteractorBackAction : InteractorAction() {
        class ObtainedDataAction(val data: List<SampleObject>, val throwable: Throwable?) : TypeInteractorBackAction()
    }
}