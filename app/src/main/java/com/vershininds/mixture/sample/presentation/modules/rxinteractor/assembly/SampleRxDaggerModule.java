package com.vershininds.mixture.sample.presentation.modules.rxinteractor.assembly;

import dagger.Module;
import dagger.Provides;
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.contract.SampleRxInteractorContract;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.contract.SampleRxRouterContract;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.contract.SampleRxVmContract;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.interactor.SampleRxInteractor;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.presenter.SampleRxPresenter;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.router.SampleRxRouter;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.viewmodel.SampleRxViewModel;

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