package com.vershininds.mixture.sample.presentation.modules.details.contract

import com.vershininds.mixture.interactor.MxtInteractor

interface DetailsInteractorContract {
    interface ViewModel : MxtInteractor.Listener
    interface Interactor : MxtInteractor<ViewModel>
}