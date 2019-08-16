package com.vershininds.mixture.sample.presentation.modules.catalog.interactor;

import com.vershininds.mixture.rx2interactor.AbstractInteractor;
import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.domain.services.ServiceMaker;
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogInteractorContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CatalogInteractor extends AbstractInteractor<CatalogInteractorContract.Presenter>
        implements CatalogInteractorContract.Interactor {

    public final static String TAG = CatalogInteractor.class.getSimpleName();

    private final ServiceMaker mServiceMaker;

    @Inject
    public CatalogInteractor(ServiceMaker serviceMaker) {
        this.mServiceMaker = serviceMaker;
    }

    @Override
    public void obtainData() {
        Single<List<SampleObject>> observable = mServiceMaker.getDataService().obtainDataRx2();

        execute(observable.toObservable(), new Callback<List<SampleObject>, CatalogInteractorContract.Presenter>() {
            @Override
            public void onResult(List<SampleObject> data, CatalogInteractorContract.Presenter target) {
                target.onObtainedData(data, null);
            }

            @Override
            public void onError(Throwable throwable, CatalogInteractorContract.Presenter target) {
                target.onObtainedData(new ArrayList<>(), throwable);
            }
        });
    }
}
