package com.example.viper.presentation.modules.sample2.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import com.example.viper.data.SampleObject;
import com.example.viper.presentation.modules.sample2.contract.Sample2InteractorContract;
import com.example.viper.presentation.modules.sample2.contract.Sample2RouterContract;
import com.example.viper.presentation.modules.sample2.contract.Sample2VmContract;
import com.vershininds.mixture.interactor.MvpInteractor;
import com.vershininds.mixture.presenter.AbstractPresenter;
import com.vershininds.mixture.view.AndroidComponent;


public class Sample2Presenter extends AbstractPresenter<Sample2VmContract.ViewModel>
        implements Sample2VmContract.Presenter, Sample2InteractorContract.Presenter, Sample2RouterContract.Presenter {

    private final Sample2InteractorContract.Interactor interactor;
    private final Sample2RouterContract.Router router;

    @Inject
    public Sample2Presenter(Sample2VmContract.ViewModel viewModel,
                            Sample2InteractorContract.Interactor interactor,
                            Sample2RouterContract.Router router) {
        super(viewModel);
        this.interactor = interactor;
        this.router = router;

        this.router.setListener(this);
        this.interactor.setListener(this);
    }


    @Override
    public void attachView(AndroidComponent component) {
        super.attachView(component);

        Sample2VmContract.ViewModel vm = getViewModel();

        switch (vm.getState()) {
            case INITIAL: {
                vm.setState(Sample2VmContract.State.LOADING);
                interactor.obtainData();
                break;
            }
            case LOADING: {
                if (interactor.getState().equals(MvpInteractor.State.IDLE)) {
                    vm.setState(Sample2VmContract.State.LOADING);
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
            applyAction(component -> getViewModel().setState(Sample2VmContract.State.LOADING));
            interactor.obtainData();
        }
    }

    @Override
    public void onObtainedData(@NonNull List<SampleObject> data, @Nullable Throwable throwable) {
        Sample2VmContract.ViewModel vm = getViewModel();
        if (throwable == null){
            if (data.isEmpty()) {
                applyAction(component -> vm.setState(Sample2VmContract.State.EMPTY));
            } else {
                applyAction(component -> vm.setState(Sample2VmContract.State.DATA));
                applyAction(component -> vm.setData(data));
            }
        } else {
            applyAction(component -> vm.setError(throwable.getMessage()));
        }
    }
}