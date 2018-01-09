package com.vershininds.mixture.sample.presentation.modules.lifecycle.assembly;

import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.contract.SampleLifecycleVmContract;

import dagger.Subcomponent;

@PerFeatureScope
@Subcomponent(modules = {SampleLifecycleDaggerModule.class})
public interface SampleLifecycleComponent {
    SampleLifecycleVmContract.Presenter getPresenter();
    SampleLifecycleVmContract.ViewModel getViewModel();
}