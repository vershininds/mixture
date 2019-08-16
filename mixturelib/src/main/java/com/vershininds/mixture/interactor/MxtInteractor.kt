package com.vershininds.mixture.interactor

interface MxtInteractor<L : MxtInteractor.Listener> {

    interface Listener

    /**
     * [Listener] target class who get operation result from interactor
     */
    var listener : L?

    fun destroy() {
        listener = null
    }
}