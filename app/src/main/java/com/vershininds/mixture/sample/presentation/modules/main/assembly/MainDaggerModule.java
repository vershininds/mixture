package com.vershininds.mixture.sample.presentation.modules.main.assembly;

import dagger.Module;
import dagger.Provides;

import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope;
import com.vershininds.mixture.sample.presentation.modules.main.contract.MainVmContract;
import com.vershininds.mixture.sample.presentation.modules.main.presenter.MainPresenter;
import com.vershininds.mixture.sample.presentation.modules.main.router.MainRouter;
import com.vershininds.mixture.sample.presentation.modules.main.viewmodel.MainViewModel;
import com.vershininds.mixture.sample.presentation.modules.main.contract.MainRouterContract;

@Module
public class MainDaggerModule {

    @PerFeatureScope
    @Provides
    MainVmContract.Presenter providesPresenter(MainPresenter presenter) {
        return presenter;
    }

    @PerFeatureScope
    @Provides
    MainRouterContract.Router providesRouter(MainRouter router) {
        return router;
    }

    @PerFeatureScope
    @Provides
    MainVmContract.ViewModel providesViewModel(MainViewModel viewModel) {
        return viewModel;
    }

}