package com.example.viper.presentation.modules.rxinteractor.assembly;

import dagger.Subcomponent;
import com.example.viper.presentation.assembly.PerFeatureScope;
import com.example.viper.presentation.modules.rxinteractor.contract.SampleRxVmContract;

@PerFeatureScope
@Subcomponent(modules = {SampleRxDaggerModule.class})
public interface SampleRxComponent {
    SampleRxVmContract.Presenter getPresenter();
    SampleRxVmContract.ViewModel getViewModel();
}