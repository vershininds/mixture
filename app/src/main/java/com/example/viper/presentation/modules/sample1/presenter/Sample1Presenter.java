package com.example.viper.presentation.modules.sample1.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import com.example.viper.data.SampleObject;
import com.example.viper.presentation.modules.sample1.contract.Sample1InteractorContract;
import com.example.viper.presentation.modules.sample1.contract.Sample1RouterContract;
import com.example.viper.presentation.modules.sample1.contract.Sample1VmContract;
import com.vershininds.mixture.interactor.MvpInteractor;
import com.vershininds.mixture.presenter.AbstractPresenter;
import com.vershininds.mixture.view.AndroidComponent;


public class Sample1Presenter extends AbstractPresenter<Sample1VmContract.ViewModel>
        implements Sample1VmContract.Presenter, Sample1InteractorContract.Presenter, Sample1RouterContract.Presenter {

    private final Sample1InteractorContract.Interactor interactor;
    private final Sample1RouterContract.Router router;

    @Inject
    public Sample1Presenter(Sample1VmContract.ViewModel viewModel,
                            Sample1InteractorContract.Interactor interactor,
                            Sample1RouterContract.Router router) {
        super(viewModel);
        this.interactor = interactor;
        this.router = router;

        this.router.setListener(this);
        this.interactor.setListener(this);
    }


    @Override
    public void attachView(AndroidComponent component) {
        super.attachView(component);

        Sample1VmContract.ViewModel vm = getViewModel();

        switch (vm.getState()) {
            case INITIAL: {
                vm.setState(Sample1VmContract.State.LOADING);
                interactor.obtainData();
                break;
            }
            case LOADING: {
                if (interactor.getState().equals(MvpInteractor.State.IDLE)) {
                    vm.setState(Sample1VmContract.State.LOADING);
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
            applyAction(component -> getViewModel().setState(Sample1VmContract.State.LOADING));
            interactor.obtainData();
        }
    }

    @Override
    public void onObtainedData(@NonNull List<SampleObject> data, @Nullable Throwable throwable) {
        Sample1VmContract.ViewModel vm = getViewModel();
        if (throwable == null){
            if (data.isEmpty()) {
                applyAction(component -> vm.setState(Sample1VmContract.State.EMPTY));
            } else {
                applyAction(component -> vm.setState(Sample1VmContract.State.DATA));
                applyAction(component -> vm.setData(data));
            }
        } else {
            applyAction(component -> vm.setError(throwable.getMessage()));
        }
    }
}