package com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract

import com.vershininds.mixture.interactor.MvpInteractor
import com.vershininds.mixture.sample.data.SampleObject

interface SampleRx2InteractorContract {

    interface Presenter : MvpInteractor.Listener {
        fun onObtainedData(data: List<SampleObject>, throwable: Throwable?)
    }

    interface Interactor : MvpInteractor<Presenter> {
        fun obtainData()
    }
}