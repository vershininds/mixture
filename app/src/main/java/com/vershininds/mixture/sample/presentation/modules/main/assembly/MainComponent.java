package com.vershininds.mixture.sample.presentation.modules.main.assembly;

import dagger.Subcomponent;

import com.vershininds.mixture.sample.presentation.modules.main.contract.MainVmContract;
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope;

@PerFeatureScope
@Subcomponent(modules = {MainDaggerModule.class})
public interface MainComponent {
    MainVmContract.Presenter getPresenter();
    MainVmContract.ViewModel getViewModel();
}