package com.example.viper.presentation.modules.sample2.assembly;

import dagger.Module;
import dagger.Provides;
import com.example.viper.presentation.assembly.PerFeatureScope;
import com.example.viper.presentation.modules.sample2.contract.Sample2InteractorContract;
import com.example.viper.presentation.modules.sample2.contract.Sample2RouterContract;
import com.example.viper.presentation.modules.sample2.contract.Sample2VmContract;
import com.example.viper.presentation.modules.sample2.interactor.Sample2Interactor;
import com.example.viper.presentation.modules.sample2.presenter.Sample2Presenter;
import com.example.viper.presentation.modules.sample2.router.Sample2Router;
import com.example.viper.presentation.modules.sample2.viewmodel.Sample2ViewModel;

@Module
public class Sample2DaggerModule {

    @PerFeatureScope
    @Provides
    Sample2VmContract.Presenter providesPresenter(Sample2Presenter presenter) {
        return presenter;
    }

    @PerFeatureScope
    @Provides
    Sample2InteractorContract.Interactor providesInteractor(Sample2Interactor interactor) {
        return interactor;
    }

    @PerFeatureScope
    @Provides
    Sample2RouterContract.Router providesRouter(Sample2Router router) {
        return router;
    }

    @PerFeatureScope
    @Provides
    Sample2VmContract.ViewModel providesViewModel(Sample2ViewModel viewModel) {
        return viewModel;
    }

}