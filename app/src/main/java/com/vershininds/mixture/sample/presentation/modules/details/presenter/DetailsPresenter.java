package com.vershininds.mixture.sample.presentation.modules.details.presenter;


import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import com.vershininds.mixture.sample.presentation.modules.details.DetailsModuleInput;
import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsInteractorContract;
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsRouterContract;
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsVmContract;
import com.vershininds.mixture.presenter.AbstractPresenter;
import com.vershininds.mixture.view.AndroidComponent;


public class DetailsPresenter extends AbstractPresenter<DetailsVmContract.ViewModel>
        implements DetailsVmContract.Presenter, DetailsInteractorContract.Presenter, DetailsRouterContract.Presenter {

    private final DetailsInteractorContract.Interactor interactor;
    private final DetailsRouterContract.Router router;

    @Inject
    public DetailsPresenter(DetailsVmContract.ViewModel viewModel, DetailsInteractorContract.Interactor interactor, DetailsRouterContract.Router router) {
        super(viewModel);
        this.interactor = interactor;
        this.router = router;
    }

    @Override
    public void attachView(AndroidComponent component) {
        super.attachView(component);

        router.setListener(this);
        interactor.setListener(this);
    }

    @Override
    public void detachView() {
        interactor.setListener(null);
        router.setListener(null);
        super.detachView();
    }

    @Override
    public void destroy() {
        super.destroy();
        interactor.reset();
    }

    @Override
    public void onClickFinish() {
        SampleObject dataObject = getViewModel().getData();
        dataObject.setName("We set other name");

        EventBus.getDefault().postSticky(new DetailsModuleInput.DetailsFinishedEvent(dataObject));
        applyAction(router::finishScreen);
    }
}