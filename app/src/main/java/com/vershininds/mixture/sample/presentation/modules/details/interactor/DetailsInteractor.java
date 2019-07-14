package com.vershininds.mixture.sample.presentation.modules.details.interactor;

import com.vershininds.mixture.rx2interactor.AbstractInteractor;
import com.vershininds.mixture.sample.domain.services.ServiceMaker;
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsInteractorContract;

import javax.inject.Inject;

public class DetailsInteractor extends AbstractInteractor<DetailsInteractorContract.Presenter>
        implements DetailsInteractorContract.Interactor {

    public final static String TAG = DetailsInteractor.class.getSimpleName();

    private final ServiceMaker mServiceMaker;

    @Inject
    public DetailsInteractor(ServiceMaker serviceMaker) {
        this.mServiceMaker = serviceMaker;
    }
}
