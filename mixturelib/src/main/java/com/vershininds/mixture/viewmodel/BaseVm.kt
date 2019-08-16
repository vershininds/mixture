package com.vershininds.mixture.viewmodel

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.vershininds.mixture.action.ActionDispatcher
import com.vershininds.mixture.action.RetentiveAction

/**
 * Class contains helpers methods for organize work logic between [ViewModel] and View.
 */
abstract class BaseVm : ViewModel() {

    /**
     * Load data when viewModel was create/
     * Call loadData in [restoreInstanceState] if (savedInstanceState == null) for first load
     * or call every time if you don't save data in Bundle
     */
    abstract fun loadData()

    protected val KEY_DATA = this::class.java.simpleName + "_KEY_DATA"

    var actionDispatcher = ActionDispatcher()
        private set

    protected open fun saveInstanceState(outState: Bundle): Bundle {
        return outState
    }

    protected open fun restoreInstanceState(savedInstanceState: Bundle?) {
    }

    protected fun postActionDispatcher(action: RetentiveAction) {
        actionDispatcher.dispatch(action)
    }
}