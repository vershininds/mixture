package com.example.viper.presentation.modules.sample1.assembly;

import dagger.Subcomponent;
import com.example.viper.presentation.assembly.PerFeatureScope;
import com.example.viper.presentation.modules.sample1.contract.Sample1VmContract;

@PerFeatureScope
@Subcomponent(modules = {Sample1DaggerModule.class})
public interface Sample1Component {
    Sample1VmContract.Presenter getPresenter();
    Sample1VmContract.ViewModel getViewModel();
}