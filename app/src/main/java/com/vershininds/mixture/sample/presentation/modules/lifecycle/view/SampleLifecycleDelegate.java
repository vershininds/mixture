package com.vershininds.mixture.sample.presentation.modules.lifecycle.view;

import android.arch.lifecycle.Lifecycle;
import android.databinding.Observable;
import android.support.annotation.NonNull;

import com.vershininds.mixture.sample.application.AppDelegate;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.assembly.SampleLifecycleComponent;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.assembly.SampleLifecycleDaggerModule;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.contract.SampleLifecycleVmContract;
import com.vershininds.mixture.view.AndroidComponent;
import com.vershininds.mixture.view.MvpDelegate;

public class SampleLifecycleDelegate extends MvpDelegate<SampleLifecycleVmContract.ViewModel, SampleLifecycleVmContract.Presenter> {

    private SampleLifecycleComponent diComponent;

    public SampleLifecycleDelegate(@NonNull Lifecycle lifecycle, @NonNull AndroidComponent component, @NonNull Observable.OnPropertyChangedCallback vmObserver) {
        super(lifecycle, component, vmObserver);
    }

    @Override
    protected void injectDi() {
        diComponent = AppDelegate.get()
                .presentationComponents()
                .sampleLifecycleComponent(new SampleLifecycleDaggerModule());
    }

    @Override
    protected SampleLifecycleVmContract.Presenter createPresenter() {
        return diComponent.getPresenter();
    }
}
