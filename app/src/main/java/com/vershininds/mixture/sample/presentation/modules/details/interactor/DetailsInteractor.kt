package com.vershininds.mixture.sample.presentation.modules.details.interactor

import com.vershininds.mixture.sample.domain.services.ServiceMaker
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsInteractorContract
import javax.inject.Inject

class DetailsInteractor @Inject constructor(
        private val serviceMaker: ServiceMaker
) : DetailsInteractorContract.Interactor {
    override var listener: DetailsInteractorContract.ViewModel? = null
}