package com.vershininds.mixture.sample.presentation.modules.rx2interactor.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2InteractorContract

@Suppress("UNCHECKED_CAST")
class SampleRx2VmFactory(
        private val interactor: SampleRx2InteractorContract.Interactor
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass == SampleRx2Vm::class.java) {
            SampleRx2Vm(interactor) as T
        } else throw IllegalStateException("Not supported modelClass $modelClass")
    }
}