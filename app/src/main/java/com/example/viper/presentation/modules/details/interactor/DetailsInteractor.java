package com.example.viper.presentation.modules.details.interactor;

import com.example.viper.domain.services.ServiceMaker;
import com.example.viper.presentation.modules.details.contract.DetailsInteractorContract;
import com.vershininds.mixture.rxinteractor.AbstractInteractor;

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
