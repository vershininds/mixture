package com.vershininds.mixture.sample.presentation.modules.rx2interactor.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2InteractorContract;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2RouterContract;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2VmContract;
import com.vershininds.mixture.interactor.MvpInteractor;
import com.vershininds.mixture.presenter.AbstractPresenter;
import com.vershininds.mixture.view.AndroidComponent;


public class SampleRx2Presenter extends AbstractPresenter<SampleRx2VmContract.ViewModel>
        implements SampleRx2VmContract.Presenter, SampleRx2InteractorContract.Presenter, SampleRx2RouterContract.Presenter {

    private final SampleRx2InteractorContract.Interactor interactor;
    private final SampleRx2RouterContract.Router router;

    @Inject
    public SampleRx2Presenter(SampleRx2VmContract.ViewModel viewModel,
                              SampleRx2InteractorContract.Interactor interactor,
                              SampleRx2RouterContract.Router router) {
        super(viewModel);
        this.interactor = interactor;
        this.router = router;

        this.router.setListener(this);
        this.interactor.setListener(this);
    }


    @Override
    public void attachView(AndroidComponent component) {
        super.attachView(component);

        SampleRx2VmContract.ViewModel vm = getViewModel();

        switch (vm.getState()) {
            case INITIAL: {
                vm.setState(SampleRx2VmContract.State.LOADING);
                interactor.obtainData();
                break;
            }
            case LOADING: {
                if (interactor.getState().equals(MvpInteractor.State.IDLE)) {
                    vm.setState(SampleRx2VmContract.State.LOADING);
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
            applyAction(component -> getViewModel().setState(SampleRx2VmContract.State.LOADING));
            interactor.obtainData();
        }
    }

    @Override
    public void onObtainedData(@NonNull List<SampleObject> data, @Nullable Throwable throwable) {
        SampleRx2VmContract.ViewModel vm = getViewModel();
        if (throwable == null){
            if (data.isEmpty()) {
                applyAction(component -> vm.setState(SampleRx2VmContract.State.EMPTY));
            } else {
                applyAction(component -> vm.setState(SampleRx2VmContract.State.DATA));
                applyAction(component -> vm.setData(data));
            }
        } else {
            applyAction(component -> vm.setError(throwable.getMessage()));
        }
    }
}