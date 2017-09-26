package com.example.viper.presentation.modules.sample1.interactor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.example.viper.data.SampleObject;
import com.example.viper.domain.services.ServiceMaker;
import com.example.viper.presentation.modules.sample1.contract.Sample1InteractorContract;
import com.vershininds.mixture.rxinteractor.AbstractInteractor;

import rx.Observable;

public class Sample1Interactor extends AbstractInteractor<Sample1InteractorContract.Presenter>
        implements Sample1InteractorContract.Interactor {

    public final static String TAG = Sample1Interactor.class.getSimpleName();

    private final ServiceMaker mServiceMaker;

    @Inject
    public Sample1Interactor(ServiceMaker serviceMaker) {
        this.mServiceMaker = serviceMaker;
    }

    @Override
    public void obtainData() {
        Observable<List<SampleObject>> observable = mServiceMaker.getDataService().obtainData();

        execute(observable, new Callback<List<SampleObject>, Sample1InteractorContract.Presenter>() {
            @Override
            public void onResult(List<SampleObject> data, Sample1InteractorContract.Presenter target) {
                target.onObtainedData(data, null);
            }

            @Override
            public void onError(Throwable throwable, Sample1InteractorContract.Presenter target) {
                target.onObtainedData(new ArrayList<>(), throwable);
            }
        });
    }
}
