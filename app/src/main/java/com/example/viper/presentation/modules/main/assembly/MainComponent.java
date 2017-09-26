package com.example.viper.presentation.modules.main.assembly;

import dagger.Subcomponent;
import com.example.viper.presentation.assembly.PerFeatureScope;
import com.example.viper.presentation.modules.main.contract.MainVmContract;

@PerFeatureScope
@Subcomponent(modules = {MainDaggerModule.class})
public interface MainComponent {
    MainVmContract.Presenter getPresenter();
    MainVmContract.ViewModel getViewModel();
}