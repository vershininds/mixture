package com.vershininds.mixture.interactor

import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.dispatcher.subscribeInteractor

abstract class BaseInteractor constructor(
        private val dispatcher: ActionDispatcher
) : MxtInteractor {

    private val tag = this::class.java.simpleName

    init {
        subscribeOnDispatcher()
    }

    final override fun subscribeOnDispatcher() {
        dispatcher.subscribeInteractor(tag) {}
    }

    override fun unsubscribeDispatcher() {
        dispatcher.removeSubscriber(tag)
    }
}