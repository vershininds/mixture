package com.vershininds.mixture.sample.presentation.modules.details.contract

import com.vershininds.mixture.interactor.MvpInteractor

interface DetailsInteractorContract {

    interface Presenter : MvpInteractor.Listener

    interface Interactor : MvpInteractor<Presenter>
}