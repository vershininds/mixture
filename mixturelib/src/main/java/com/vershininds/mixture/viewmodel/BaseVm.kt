package com.vershininds.mixture.viewmodel

import android.os.Bundle
import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.dispatcher.DispatcherSubscriber
import com.vershininds.mixture.action.RetentiveAction

/**
 * Class contains helpers methods for organize work logic between ViewModel and View.
 * ViewModel is not a ViewModel from AAC, it's a ViewModel from MVVM or like Presenter from MVP/VIPER
 */
abstract class BaseVm(private val dispatcher: ActionDispatcher): DispatcherSubscriber {

    /**
     * Load data when viewModel was created/
     * Call loadData in [restoreInstanceState] if (savedInstanceState == null) for first load
     * or call every time if you don't save data in Bundle
     */
    abstract fun loadData()

    protected val KEY_DATA = this::class.java.simpleName + "_KEY_DATA"

    override fun unsubscribeDispatcher() {
        dispatcher.removeAllSubscribers()
    }

    protected open fun saveInstanceState(outState: Bundle): Bundle {
        return outState
    }

    protected open fun restoreInstanceState(savedInstanceState: Bundle?) {
    }

    protected fun postActionDispatcher(action: RetentiveAction) {
        dispatcher.dispatch(action)
    }
}