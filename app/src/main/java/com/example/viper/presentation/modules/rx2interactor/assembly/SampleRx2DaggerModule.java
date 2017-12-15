package com.example.viper.presentation.modules.rx2interactor.assembly;

import dagger.Module;
import dagger.Provides;
import com.example.viper.presentation.assembly.PerFeatureScope;
import com.example.viper.presentation.modules.rx2interactor.contract.SampleRx2InteractorContract;
import com.example.viper.presentation.modules.rx2interactor.contract.SampleRx2RouterContract;
import com.example.viper.presentation.modules.rx2interactor.contract.SampleRx2VmContract;
import com.example.viper.presentation.modules.rx2interactor.interactor.SampleRx2Interactor;
import com.example.viper.presentation.modules.rx2interactor.presenter.SampleRx2Presenter;
import com.example.viper.presentation.modules.rx2interactor.router.SampleRx2Router;
import com.example.viper.presentation.modules.rx2interactor.viewmodel.SampleRx2ViewModel;

@Module
public class SampleRx2DaggerModule {

    @PerFeatureScope
    @Provides
    SampleRx2VmContract.Presenter providesPresenter(SampleRx2Presenter presenter) {
        return presenter;
    }

    @PerFeatureScope
    @Provides
    SampleRx2InteractorContract.Interactor providesInteractor(SampleRx2Interactor interactor) {
        return interactor;
    }

    @PerFeatureScope
    @Provides
    SampleRx2RouterContract.Router providesRouter(SampleRx2Router router) {
        return router;
    }

    @PerFeatureScope
    @Provides
    SampleRx2VmContract.ViewModel providesViewModel(SampleRx2ViewModel viewModel) {
        return viewModel;
    }

}