package com.vershininds.mixture.sample.presentation.modules.catalog.contract

import com.vershininds.mixture.interactor.MxtInteractor
import com.vershininds.mixture.sample.data.SampleObject

interface CatalogInteractorContract {

    interface ViewModel : MxtInteractor.Listener {
        fun onObtainedData(data: List<SampleObject>, throwable: Throwable?)
    }

    interface Interactor : MxtInteractor<ViewModel> {
        fun obtainData()
    }
}