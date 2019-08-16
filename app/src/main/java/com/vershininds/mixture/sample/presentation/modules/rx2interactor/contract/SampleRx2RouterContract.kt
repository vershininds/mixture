package com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract;


import com.vershininds.mixture.action.RouterAction
import com.vershininds.mixture.router.MviRouter
import com.vershininds.mixture.router.MvpRouter
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2RouterContract.Router
import com.vershininds.mixture.view.AndroidComponent


/**
 * Contract between [ViewModel] and [Router]
 */

interface SampleRx2RouterContract {

    /**
     * It's only sample how post more than one parameter in router
     *
     * @param sampleObject some data object
     */
    data class DetailsParams(val sampleObject: SampleObject)

    sealed class TypeRouterAction : RouterAction() {
        class DetailsScreenAction(val params: DetailsParams) : TypeRouterAction()
    }

    interface Router : MvpRouter<MvpRouter.Listener>, MviRouter<TypeRouterAction> {
        /**
         * @param androidComponent [AndroidComponent]
         */
        fun showDetailsScreen(androidComponent: AndroidComponent, params: DetailsParams)
    }
}