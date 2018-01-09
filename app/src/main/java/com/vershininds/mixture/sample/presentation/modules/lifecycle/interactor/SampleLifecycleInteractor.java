package com.vershininds.mixture.sample.presentation.modules.lifecycle.interactor;

import com.vershininds.mixture.rxinteractor.AbstractInteractor;
import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.domain.services.ServiceMaker;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.contract.SampleLifecycleInteractorContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class SampleLifecycleInteractor extends AbstractInteractor<SampleLifecycleInteractorContract.Presenter>
        implements SampleLifecycleInteractorContract.Interactor {

    public final static String TAG = SampleLifecycleInteractor.class.getSimpleName();

    private final ServiceMaker mServiceMaker;

    @Inject
    public SampleLifecycleInteractor(ServiceMaker serviceMaker) {
        this.mServiceMaker = serviceMaker;
    }

    @Override
    public void obtainData() {
        Observable<List<SampleObject>> observable = mServiceMaker.getDataService().obtainData();

        execute(observable, new Callback<List<SampleObject>, SampleLifecycleInteractorContract.Presenter>() {
            @Override
            public void onResult(List<SampleObject> data, SampleLifecycleInteractorContract.Presenter target) {
                target.onObtainedData(data, null);
            }

            @Override
            public void onError(Throwable throwable, SampleLifecycleInteractorContract.Presenter target) {
                target.onObtainedData(new ArrayList<>(), throwable);
            }
        });
    }
}
