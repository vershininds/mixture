package com.example.viper.presentation.modules.details.assembly;

import dagger.Subcomponent;
import com.example.viper.presentation.assembly.PerFeatureScope;
import com.example.viper.presentation.modules.details.contract.DetailsVmContract;

@PerFeatureScope
@Subcomponent(modules = {DetailsDaggerModule.class})
public interface DetailsComponent {
    DetailsVmContract.Presenter getPresenter();
    DetailsVmContract.ViewModel getViewModel();
}