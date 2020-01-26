package com.vershininds.mixture.router

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.vershininds.mixture.dispatcher.DispatcherSubscriber

interface MxtRouter2: DispatcherSubscriber

fun MxtRouter2.manageBy(lifecycleOwner: LifecycleOwner) {
    val observer = object : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart() {
            subscribeOnDispatcher()
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() {
            unsubscribeDispatcher()
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            lifecycleOwner.lifecycle.removeObserver(this)
        }

    }
    lifecycleOwner.lifecycle.addObserver(observer)
}