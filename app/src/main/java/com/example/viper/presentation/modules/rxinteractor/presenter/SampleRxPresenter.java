package com.example.viper.presentation.modules.rxinteractor.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import com.example.viper.data.SampleObject;
import com.example.viper.presentation.modules.rxinteractor.contract.SampleRxInteractorContract;
import com.example.viper.presentation.modules.rxinteractor.contract.SampleRxRouterContract;
import com.example.viper.presentation.modules.rxinteractor.contract.SampleRxVmContract;
import com.vershininds.mixture.interactor.MvpInteractor;
import com.vershininds.mixture.presenter.AbstractPresenter;
import com.vershininds.mixture.view.AndroidComponent;


public class SampleRxPresenter extends AbstractPresenter<SampleRxVmContract.ViewModel>
        implements SampleRxVmContract.Presenter, SampleRxInteractorContract.Presenter, SampleRxRouterContract.Presenter {

    private final SampleRxInteractorContract.Interactor interactor;
    private final SampleRxRouterContract.Router router;

    @Inject
    public SampleRxPresenter(SampleRxVmContract.ViewModel viewModel,
                             SampleRxInteractorContract.Interactor interactor,
                             SampleRxRouterContract.Router router) {
        super(viewModel);
        this.interactor = interactor;
        this.router = router;

        this.router.setListener(this);
        this.interactor.setListener(this);
    }


    @Override
    public void attachView(AndroidComponent component) {
        super.attachView(component);

        SampleRxVmContract.ViewModel vm = getViewModel();

        switch (vm.getState()) {
            case INITIAL: {
                vm.setState(SampleRxVmContract.State.LOADING);
                interactor.obtainData();
                break;
            }
            case LOADING: {
                if (interactor.getState().equals(MvpInteractor.State.IDLE)) {
                    vm.setState(SampleRxVmContract.State.LOADING);
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
            applyAction(component -> getViewModel().setState(SampleRxVmContract.State.LOADING));
            interactor.obtainData();
        }
    }

    @Override
    public void onObtainedData(@NonNull List<SampleObject> data, @Nullable Throwable throwable) {
        SampleRxVmContract.ViewModel vm = getViewModel();
        if (throwable == null){
            if (data.isEmpty()) {
                applyAction(component -> vm.setState(SampleRxVmContract.State.EMPTY));
            } else {
                applyAction(component -> vm.setState(SampleRxVmContract.State.DATA));
                applyAction(component -> vm.setData(data));
            }
        } else {
            applyAction(component -> vm.setError(throwable.getMessage()));
        }
    }
}