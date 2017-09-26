package com.example.viper.presentation.modules.details.assembly;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import com.example.viper.data.SampleObject;
import com.example.viper.presentation.assembly.PerFeatureScope;
import com.example.viper.presentation.modules.details.contract.DetailsInteractorContract;
import com.example.viper.presentation.modules.details.contract.DetailsRouterContract;
import com.example.viper.presentation.modules.details.contract.DetailsVmContract;
import com.example.viper.presentation.modules.details.interactor.DetailsInteractor;
import com.example.viper.presentation.modules.details.presenter.DetailsPresenter;
import com.example.viper.presentation.modules.details.router.DetailsRouter;
import com.example.viper.presentation.modules.details.viewmodel.DetailsViewModel;

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