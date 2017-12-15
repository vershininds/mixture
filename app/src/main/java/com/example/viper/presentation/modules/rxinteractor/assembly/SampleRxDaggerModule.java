package com.example.viper.presentation.modules.rxinteractor.assembly;

import dagger.Module;
import dagger.Provides;
import com.example.viper.presentation.assembly.PerFeatureScope;
import com.example.viper.presentation.modules.rxinteractor.contract.SampleRxInteractorContract;
import com.example.viper.presentation.modules.rxinteractor.contract.SampleRxRouterContract;
import com.example.viper.presentation.modules.rxinteractor.contract.SampleRxVmContract;
import com.example.viper.presentation.modules.rxinteractor.interactor.SampleRxInteractor;
import com.example.viper.presentation.modules.rxinteractor.presenter.SampleRxPresenter;
import com.example.viper.presentation.modules.rxinteractor.router.SampleRxRouter;
import com.example.viper.presentation.modules.rxinteractor.viewmodel.SampleRxViewModel;

@Module
public class SampleRxDaggerModule {

    @PerFeatureScope
    @Provides
    SampleRxVmContract.Presenter providesPresenter(SampleRxPresenter presenter) {
        return presenter;
    }

    @PerFeatureScope
    @Provides
    SampleRxInteractorContract.Interactor providesInteractor(SampleRxInteractor interactor) {
        return interactor;
    }

    @PerFeatureScope
    @Provides
    SampleRxRouterContract.Router providesRouter(SampleRxRouter router) {
        return router;
    }

    @PerFeatureScope
    @Provides
    SampleRxVmContract.ViewModel providesViewModel(SampleRxViewModel viewModel) {
        return viewModel;
    }

}