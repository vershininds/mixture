package com.vershininds.mixture.sample.presentation.modules.lifecycle.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vershininds.mixture.interactor.MvpInteractor;
import com.vershininds.mixture.presenter.AbstractPresenter;
import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.contract.SampleLifecycleInteractorContract;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.contract.SampleLifecycleRouterContract;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.contract.SampleLifecycleVmContract;
import com.vershininds.mixture.view.AndroidComponent;

import java.util.List;

import javax.inject.Inject;


public class SampleLifecyclePresenter extends AbstractPresenter<SampleLifecycleVmContract.ViewModel>
        implements SampleLifecycleVmContract.Presenter, SampleLifecycleInteractorContract.Presenter, SampleLifecycleRouterContract.Presenter {

    private final SampleLifecycleInteractorContract.Interactor interactor;
    private final SampleLifecycleRouterContract.Router router;

    @Inject
    public SampleLifecyclePresenter(SampleLifecycleVmContract.ViewModel viewModel,
                                    SampleLifecycleInteractorContract.Interactor interactor,
                                    SampleLifecycleRouterContract.Router router) {
        super(viewModel);
        this.interactor = interactor;
        this.router = router;

        this.router.setListener(this);
        this.interactor.setListener(this);
    }


    @Override
    public void attachView(AndroidComponent component) {
        super.attachView(component);

        SampleLifecycleVmContract.ViewModel vm = getViewModel();

        switch (vm.getState()) {
            case INITIAL: {
                vm.setState(SampleLifecycleVmContract.State.LOADING);
                interactor.obtainData();
                break;
            }
            case LOADING: {
                if (interactor.getState().equals(MvpInteractor.State.IDLE)) {
                    vm.setState(SampleLifecycleVmContract.State.LOADING);
                    interactor.obtainData();
                }
                break;
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        interactor.setListener(null);
        router.setListener(null);
        interactor.destroy();
    }

    @Override
    public void itemSelected(SampleObject item) {
        applyAction(component -> router.showDetailsScreen(component, item));
    }

    @Override
    public void retry() {
        if (interactor.getState().equals(MvpInteractor.State.IDLE)) {
            applyAction(component -> getViewModel().setState(SampleLifecycleVmContract.State.LOADING));
            interactor.obtainData();
        }
    }

    @Override
    public void onObtainedData(@NonNull List<SampleObject> data, @Nullable Throwable throwable) {
        SampleLifecycleVmContract.ViewModel vm = getViewModel();
        if (throwable == null){
            if (data.isEmpty()) {
                applyAction(component -> vm.setState(SampleLifecycleVmContract.State.EMPTY));
            } else {
                applyAction(component -> vm.setState(SampleLifecycleVmContract.State.DATA));
                applyAction(component -> vm.setData(data));
            }
        } else {
            applyAction(component -> vm.setError(throwable.getMessage()));
        }
    }
}