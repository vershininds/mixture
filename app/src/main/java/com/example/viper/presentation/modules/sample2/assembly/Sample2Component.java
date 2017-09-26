package com.example.viper.presentation.modules.sample2.assembly;

import dagger.Subcomponent;
import com.example.viper.presentation.assembly.PerFeatureScope;
import com.example.viper.presentation.modules.sample2.contract.Sample2VmContract;

@PerFeatureScope
@Subcomponent(modules = {Sample2DaggerModule.class})
public interface Sample2Component {
    Sample2VmContract.Presenter getPresenter();
    Sample2VmContract.ViewModel getViewModel();
}