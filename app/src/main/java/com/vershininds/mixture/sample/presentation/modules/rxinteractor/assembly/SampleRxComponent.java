package com.vershininds.mixture.sample.presentation.modules.rxinteractor.assembly;

import dagger.Subcomponent;
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.contract.SampleRxVmContract;

@PerFeatureScope
@Subcomponent(modules = {SampleRxDaggerModule.class})
public interface SampleRxComponent {
    SampleRxVmContract.Presenter getPresenter();
    SampleRxVmContract.ViewModel getViewModel();
}