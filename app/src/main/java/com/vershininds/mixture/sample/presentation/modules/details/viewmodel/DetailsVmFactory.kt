package com.vershininds.mixture.sample.presentation.modules.details.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsInteractorContract

@Suppress("UNCHECKED_CAST")
class DetailsVmFactory(
        private val interactor: DetailsInteractorContract.Interactor,
        private val sampleObject: SampleObject
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass == DetailsVm::class.java) {
            DetailsVm(interactor, sampleObject) as T
        } else throw IllegalStateException("Not supported modelClass $modelClass")
    }
}