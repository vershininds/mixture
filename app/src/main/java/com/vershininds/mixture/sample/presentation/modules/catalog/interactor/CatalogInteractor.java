package com.vershininds.mixture.sample.presentation.modules.catalog.interactor;

import com.vershininds.mixture.mngtask.AbstractInteractor;
import com.vershininds.mixture.mngtask.action.reactive.SingleTaskKt;
import com.vershininds.mixture.mngtask.executor.TaskExecutorRx;
import com.vershininds.mixture.mngtask.strategy.Default;
import com.vershininds.mixture.mngtask.strategy.GroupKt;
import com.vershininds.mixture.mngtask.strategy.SaveMe;
import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.domain.services.ServiceMaker;
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogInteractorContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import kotlin.Unit;

public class CatalogInteractor extends AbstractInteractor<CatalogInteractorContract.Presenter>
        implements CatalogInteractorContract.Interactor {

    public final static String TAG = CatalogInteractor.class.getSimpleName();

    private final ServiceMaker mServiceMaker;
    private final TaskExecutorRx executor;

    @Inject
    public CatalogInteractor(ServiceMaker serviceMaker) {
        this.mServiceMaker = serviceMaker;
        executor = new TaskExecutorRx();
    }

    @Override
    public void destroy() {
        executor.cancelAll();
        super.destroy();
    }

    @Override
    public void obtainData() {
        Single<List<SampleObject>> observable = mServiceMaker.getDataService().obtainDataRx2();
        executor.execute(SingleTaskKt.toTaskFuture(
                observable,
                "obtainData",
                SaveMe.INSTANCE,
                Default.INSTANCE,
                GroupKt.DEFAULT_GROUP
                ), sampleObjects -> {
                    CatalogInteractorContract.Presenter listener = getListener();
                    if (listener != null) getListener().onObtainedData(sampleObjects, null);
                    return Unit.INSTANCE;
                }, throwable -> {
                    CatalogInteractorContract.Presenter listener = getListener();
                    if (listener != null) getListener().onObtainedData(new ArrayList<>(), throwable);
                    return Unit.INSTANCE;
                }
        );
    }
}
