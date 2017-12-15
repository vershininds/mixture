package com.example.viper.presentation.modules.rxinteractor.interactor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.example.viper.data.SampleObject;
import com.example.viper.domain.services.ServiceMaker;
import com.example.viper.presentation.modules.rxinteractor.contract.SampleRxInteractorContract;
import com.vershininds.mixture.rxinteractor.AbstractInteractor;

import rx.Observable;

public class SampleRxInteractor extends AbstractInteractor<SampleRxInteractorContract.Presenter>
        implements SampleRxInteractorContract.Interactor {

    public final static String TAG = SampleRxInteractor.class.getSimpleName();

    private final ServiceMaker mServiceMaker;

    @Inject
    public SampleRxInteractor(ServiceMaker serviceMaker) {
        this.mServiceMaker = serviceMaker;
    }

    @Override
    public void obtainData() {
        Observable<List<SampleObject>> observable = mServiceMaker.getDataService().obtainData();

        execute(observable, new Callback<List<SampleObject>, SampleRxInteractorContract.Presenter>() {
            @Override
            public void onResult(List<SampleObject> data, SampleRxInteractorContract.Presenter target) {
                target.onObtainedData(data, null);
            }

            @Override
            public void onError(Throwable throwable, SampleRxInteractorContract.Presenter target) {
                target.onObtainedData(new ArrayList<>(), throwable);
            }
        });
    }
}
