package com.example.viper.presentation.modules.rx2interactor.assembly;

import dagger.Subcomponent;
import com.example.viper.presentation.assembly.PerFeatureScope;
import com.example.viper.presentation.modules.rx2interactor.contract.SampleRx2VmContract;

@PerFeatureScope
@Subcomponent(modules = {SampleRx2DaggerModule.class})
public interface SampleRx2Component {
    SampleRx2VmContract.Presenter getPresenter();
    SampleRx2VmContract.ViewModel getViewModel();
}