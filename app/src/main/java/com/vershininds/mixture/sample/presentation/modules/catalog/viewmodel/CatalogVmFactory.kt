package com.vershininds.mixture.sample.presentation.modules.catalog.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogInteractorContract

@Suppress("UNCHECKED_CAST")
class CatalogVmFactory(
        private val interactor: CatalogInteractorContract.Interactor
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass == CatalogVm::class.java) {
            CatalogVm(interactor) as T
        } else throw IllegalStateException("Not supported modelClass $modelClass")
    }
}