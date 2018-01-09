package com.vershininds.mixture.sample.presentation.modules.lifecycle.assembly;

import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.contract.SampleLifecycleInteractorContract;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.contract.SampleLifecycleRouterContract;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.contract.SampleLifecycleVmContract;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.interactor.SampleLifecycleInteractor;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.presenter.SampleLifecyclePresenter;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.router.SampleLifecycleRouter;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.viewmodel.SampleLifecycleViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class SampleLifecycleDaggerModule {

    @PerFeatureScope
    @Provides
    SampleLifecycleVmContract.Presenter providesPresenter(SampleLifecyclePresenter presenter) {
        return presenter;
    }

    @PerFeatureScope
    @Provides
    SampleLifecycleInteractorContract.Interactor providesInteractor(SampleLifecycleInteractor interactor) {
        return interactor;
    }

    @PerFeatureScope
    @Provides
    SampleLifecycleRouterContract.Router providesRouter(SampleLifecycleRouter router) {
        return router;
    }

    @PerFeatureScope
    @Provides
    SampleLifecycleVmContract.ViewModel providesViewModel(SampleLifecycleViewModel viewModel) {
        return viewModel;
    }

}