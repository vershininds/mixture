package com.vershininds.mixture.sample.presentation.modules.catalog.interactor

import com.vershininds.mixture.mngtask.action.reactive.toTaskFuture
import com.vershininds.mixture.mngtask.executor.TaskExecutorRx
import com.vershininds.mixture.sample.domain.services.ServiceMaker
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogInteractorContract
import javax.inject.Inject

class CatalogInteractor @Inject constructor(private val serviceMaker: ServiceMaker) : CatalogInteractorContract.Interactor {

    private val executor : TaskExecutorRx = TaskExecutorRx()

    override var listener: CatalogInteractorContract.ViewModel? = null

    override fun destroy() {
        executor.cancelAll()
        super.destroy()
    }

    override fun obtainData() {
        val observable = serviceMaker.dataService.obtainDataRx2()
        executor.execute(observable.toTaskFuture("obtainData"),
                { sampleObjects -> listener?.onObtainedData(sampleObjects, null) },
                { throwable -> listener?.onObtainedData(emptyList(), throwable) }
        )
    }
}