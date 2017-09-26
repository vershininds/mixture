package com.example.viper.presentation.modules.sample1.assembly;

import dagger.Module;
import dagger.Provides;
import com.example.viper.presentation.assembly.PerFeatureScope;
import com.example.viper.presentation.modules.sample1.contract.Sample1InteractorContract;
import com.example.viper.presentation.modules.sample1.contract.Sample1RouterContract;
import com.example.viper.presentation.modules.sample1.contract.Sample1VmContract;
import com.example.viper.presentation.modules.sample1.interactor.Sample1Interactor;
import com.example.viper.presentation.modules.sample1.presenter.Sample1Presenter;
import com.example.viper.presentation.modules.sample1.router.Sample1Router;
import com.example.viper.presentation.modules.sample1.viewmodel.Sample1ViewModel;

@Module
public class Sample1DaggerModule {

    @PerFeatureScope
    @Provides
    Sample1VmContract.Presenter providesPresenter(Sample1Presenter presenter) {
        return presenter;
    }

    @PerFeatureScope
    @Provides
    Sample1InteractorContract.Interactor providesInteractor(Sample1Interactor interactor) {
        return interactor;
    }

    @PerFeatureScope
    @Provides
    Sample1RouterContract.Router providesRouter(Sample1Router router) {
        return router;
    }

    @PerFeatureScope
    @Provides
    Sample1VmContract.ViewModel providesViewModel(Sample1ViewModel viewModel) {
        return viewModel;
    }

}