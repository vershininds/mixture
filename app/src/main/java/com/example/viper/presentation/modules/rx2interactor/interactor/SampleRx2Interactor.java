package com.example.viper.presentation.modules.rx2interactor.interactor;

import com.example.viper.data.SampleObject;
import com.example.viper.domain.services.ServiceMaker;
import com.example.viper.presentation.modules.rx2interactor.contract.SampleRx2InteractorContract;
import com.vershininds.mixture.rx2interactor.AbstractInteractor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class SampleRx2Interactor extends AbstractInteractor<SampleRx2InteractorContract.Presenter>
        implements SampleRx2InteractorContract.Interactor {

    public final static String TAG = SampleRx2Interactor.class.getSimpleName();

    private final ServiceMaker mServiceMaker;

    @Inject
    public SampleRx2Interactor(ServiceMaker serviceMaker) {
        this.mServiceMaker = serviceMaker;
    }

    @Override
    public void obtainData() {
        Single<List<SampleObject>> observable = mServiceMaker.getDataService().obtainDataRx2();

        execute(observable.toObservable(), new Callback<List<SampleObject>, SampleRx2InteractorContract.Presenter>() {
            @Override
            public void onResult(List<SampleObject> data, SampleRx2InteractorContract.Presenter target) {
                target.onObtainedData(data, null);
            }

            @Override
            public void onError(Throwable throwable, SampleRx2InteractorContract.Presenter target) {
                target.onObtainedData(new ArrayList<>(), throwable);
            }
        });
    }
}
