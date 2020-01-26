package com.vershininds.mixture.sample.presentation.modules.details.interactor

import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.interactor.BaseInteractor
import com.vershininds.mixture.sample.domain.services.ServiceMaker
import javax.inject.Inject

class DetailsInteractor @Inject constructor(
        private val dispatcher: ActionDispatcher,
        private val serviceMaker: ServiceMaker
) : BaseInteractor(dispatcher)