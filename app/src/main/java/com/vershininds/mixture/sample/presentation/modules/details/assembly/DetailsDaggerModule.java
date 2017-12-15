package com.vershininds.mixture.sample.presentation.modules.details.assembly;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsVmContract;
import com.vershininds.mixture.sample.presentation.modules.details.presenter.DetailsPresenter;
import com.vershininds.mixture.sample.presentation.modules.details.router.DetailsRouter;
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope;
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsInteractorContract;
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsRouterContract;
import com.vershininds.mixture.sample.presentation.modules.details.interactor.DetailsInteractor;
import com.vershininds.mixture.sample.presentation.modules.details.viewmodel.DetailsViewModel;

@Module
public class DetailsDaggerModule {

    private final SampleObject data;

    public DetailsDaggerModule(@NonNull SampleObject data) {
        this.data = data;
    }

    @PerFeatureScope
    @Provides
    DetailsVmContract.Presenter providesPresenter(DetailsPresenter presenter) {
        return presenter;
    }

    @PerFeatureScope
    @Provides
    DetailsInteractorContract.Interactor providesInteractor(DetailsInteractor interactor) {
        return interactor;
    }

    @PerFeatureScope
    @Provides
    DetailsRouterContract.Router providesRouter(DetailsRouter router) {
        return router;
    }

    @PerFeatureScope
    @Provides
    DetailsVmContract.ViewModel providesViewModel() {
        return new DetailsViewModel(data);
    }

}