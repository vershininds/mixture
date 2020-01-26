package com.vershininds.mixture.dispatcher

interface DispatcherSubscriber {

    fun subscribeOnDispatcher()

    fun unsubscribeDispatcher()

    fun destroy() {
        unsubscribeDispatcher()
    }
}