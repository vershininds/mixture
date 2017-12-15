package com.example.viper.presentation.modules.sample2.interactor;

import com.example.viper.data.SampleObject;
import com.example.viper.domain.services.ServiceMaker;
import com.example.viper.presentation.modules.sample2.contract.Sample2InteractorContract;
import com.vershininds.mixture.rx2interactor.AbstractInteractor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

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
        Single<List<SampleObject>> observable = mServiceMaker.getDataService().obtainDataRx2();

        execute(observable.toObservable(), new Callback<List<SampleObject>, Sample2InteractorContract.Presenter>() {
            @Override
            public void onResult(List<SampleObject> data, Sample2InteractorContract.Presenter target) {
                target.onObtainedData(data, null);
            }

            @Override
            public void onError(Throwable throwable, Sample2InteractorContract.Presenter target) {
                target.onObtainedData(new ArrayList<>(), throwable);
            }
        });
    }
}
