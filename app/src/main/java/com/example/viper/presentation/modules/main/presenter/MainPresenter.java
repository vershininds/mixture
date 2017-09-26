package com.example.viper.presentation.modules.main.presenter;

import javax.inject.Inject;

import com.example.viper.presentation.modules.main.contract.MainRouterContract;
import com.example.viper.presentation.modules.main.contract.MainVmContract;
import com.vershininds.mixture.presenter.AbstractPresenter;
import com.vershininds.mixture.view.AndroidComponent;

public class MainPresenter extends AbstractPresenter<MainVmContract.ViewModel>
        implements MainVmContract.Presenter {
    private MainRouterContract.Router router;

    @Inject
    public MainPresenter(MainVmContract.ViewModel viewModel, MainRouterContract.Router router) {
        super(viewModel);
        this.router = router;
    }


    @Override
    public void attachView(AndroidComponent component) {
        super.attachView(component);
        MainVmContract.ViewModel vm = getViewModel();

        switch (vm.getState()) {
            case INITIAL:
                showSample1();
                break;
            default:
                break;
        }

//        EventBus.getDefault().register(this);
    }

    @Override
    public void detachView() {
//        EventBus.getDefault().unregister(this);

        super.detachView();
    }

    @Override
    public void showSample1() {
        MainVmContract.ViewModel vm = getViewModel();

        if(vm.getState() != MainVmContract.State.SAMPLE_1) {
            applyAction(component -> router.showSample1Screen(component));
            applyAction(component -> vm.setState(MainVmContract.State.SAMPLE_1));
        }
    }

    @Override
    public void showSample2() {
        MainVmContract.ViewModel vm = getViewModel();
        if(vm.getState() != MainVmContract.State.SAMPLE_2) {
            applyAction(component -> router.showSample2Screen(component));
            applyAction(component -> vm.setState(MainVmContract.State.SAMPLE_2));
        }
    }

//    @Subscribe
//    public void onItemSelected(final Sample2ModuleInput.ItemSelectedEvent event) {
//        router.showDetailScreen(getAndroidComponent(), event.data);
//    }

}
