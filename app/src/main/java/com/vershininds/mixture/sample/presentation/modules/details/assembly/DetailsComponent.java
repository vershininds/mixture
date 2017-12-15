package com.vershininds.mixture.sample.presentation.modules.details.assembly;

import dagger.Subcomponent;

import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope;
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsVmContract;

@PerFeatureScope
@Subcomponent(modules = {DetailsDaggerModule.class})
public interface DetailsComponent {
    DetailsVmContract.Presenter getPresenter();
    DetailsVmContract.ViewModel getViewModel();
}