package com.example.viper.presentation.modules.sample2.interactor;

import com.example.viper.data.SampleObject;
import com.example.viper.domain.services.ServiceMaker;
import com.example.viper.presentation.modules.sample2.contract.Sample2InteractorContract;
import com.vershininds.mixture.rxinteractor.AbstractInteractor;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class Sample2Interactor extends AbstractInteractor<Sample2InteractorContract.Presenter>
        implements Sample2InteractorContract.Interactor {

    public final static String TAG = Sample2Interactor.class.getSimpleName();

    private final ServiceMaker mServiceMaker;

    @Inject
    public Sample2Interactor(ServiceMaker serviceMaker) {
        this.mServiceMaker = serviceMaker;
    }

    @Override
    public void obtainData() {
        Observable<List<SampleObject>> observable = mServiceMaker.getDataService().obtainData();

        execute(observable, new ResultFactory<List<SampleObject>, Sample2InteractorContract.Presenter>() {
            @Override
            public PendingResult<List<SampleObject>, Sample2InteractorContract.Presenter> create(List<SampleObject> data, Throwable throwable) {
                return new PendingResult<List<SampleObject>, Sample2InteractorContract.Presenter>(data, throwable) {
                    public void deliver(Sample2InteractorContract.Presenter target) {
                        List<SampleObject> result = getData();
                        target.onObtainedData(result, getError());
                    }
                };
            }
        });
    }
}
