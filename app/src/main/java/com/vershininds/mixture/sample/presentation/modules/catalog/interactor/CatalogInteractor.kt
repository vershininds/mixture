package com.vershininds.mixture.sample.presentation.modules.catalog.interactor

import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.action.InteractorAction
import com.vershininds.mixture.action.handle
import com.vershininds.mixture.dispatcher.subscribeInteractor
import com.vershininds.mixture.interactor.MxtInteractor
import com.vershininds.mixture.mngtask.action.reactive.toTaskFuture
import com.vershininds.mixture.mngtask.executor.TaskExecutorRx
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.domain.services.DataService
import com.vershininds.mixture.sample.domain.services.ServiceMaker
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogInteractorContract
import javax.inject.Inject

class CatalogInteractor @Inject constructor(
        private val dispatcher: ActionDispatcher,
        private val serviceMaker: ServiceMaker
) : MxtInteractor {

    private val tag = CatalogInteractor::class.java.simpleName

    private val executor: TaskExecutorRx = TaskExecutorRx()

    init {
        subscribeOnDispatcher()
    }

    override fun subscribeOnDispatcher() {
        dispatcher.subscribeInteractor(tag) { action: InteractorAction ->
            when (action) {
                is CatalogInteractorContract.TypeInteractorAction.ObtainDataAction -> action.handle {
                    obtainData(DataService.LoadType.NORMAL)
                }
                is CatalogInteractorContract.TypeInteractorAction.ObtainErrorDataAction -> action.handle {
                    obtainData(DataService.LoadType.ERROR_DATA)
                }
                is CatalogInteractorContract.TypeInteractorAction.ObtainEmptyDataAction -> action.handle {
                    obtainData(DataService.LoadType.EMPTY_DATA)
                }
            }
        }
    }

    override fun unsubscribeDispatcher() {
        dispatcher.removeSubscriber(tag)
    }

    override fun destroy() {
        executor.cancelAll()
        unsubscribeDispatcher()
        super.destroy()
    }

    private fun obtainData(type : DataService.LoadType) {
        val observable = serviceMaker.dataService.obtainDataRx(type)
        executor.execute(observable.toTaskFuture("obtainData"),
                { sampleObjects -> dispatcher.dispatch(createBackAction(sampleObjects, null)) },
                { throwable -> dispatcher.dispatch(createBackAction(emptyList(), throwable)) }
        )
    }

    private fun createBackAction(data: List<SampleObject>, throwable: Throwable?): CatalogInteractorContract.TypeInteractorBackAction {
        return CatalogInteractorContract.TypeInteractorBackAction.ObtainedDataAction(data, throwable)
    }
}