package com.vershininds.mixture.sample.presentation.modules.rx2interactor.assembly;

import dagger.Subcomponent;

import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2VmContract;

@PerFeatureScope
@Subcomponent(modules = {SampleRx2DaggerModule.class})
public interface SampleRx2Component {
    SampleRx2VmContract.Presenter getPresenter();
    SampleRx2VmContract.ViewModel getViewModel();
}