package com.example.viper.presentation.modules.main.assembly;

import dagger.Module;
import dagger.Provides;
import com.example.viper.presentation.assembly.PerFeatureScope;
import com.example.viper.presentation.modules.main.contract.MainRouterContract;
import com.example.viper.presentation.modules.main.contract.MainVmContract;
import com.example.viper.presentation.modules.main.presenter.MainPresenter;
import com.example.viper.presentation.modules.main.router.MainRouter;
import com.example.viper.presentation.modules.main.viewmodel.MainViewModel;

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