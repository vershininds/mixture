package com.vershininds.mixture.viewmodel

open class DataModel<DATA, ERROR>(var data: DATA? = null,
                                  var error: ERROR? = null,
                                  var state: State = State.LOADING) {

    enum class State {
        LOADING, ERROR, DATA
    }

    fun setDataWithState(data: DATA?) {
        state = State.DATA
        this.data = data
    }

    fun setErrorWithState(error: ERROR?) {
        state = State.ERROR
        this.error = error
    }

    fun updateNonNullData(state: State, updateAction: (data: DATA) -> Unit) {
        data?.let {
            this.state = state
            updateAction.invoke(it)
        }
    }
}