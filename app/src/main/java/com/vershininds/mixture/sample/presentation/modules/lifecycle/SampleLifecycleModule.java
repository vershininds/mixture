package com.vershininds.mixture.sample.presentation.modules.lifecycle;

import android.support.v4.app.Fragment;

import com.vershininds.mixture.sample.presentation.modules.lifecycle.view.SampleLifecycleFragment;

import javax.inject.Inject;


public class SampleLifecycleModule implements SampleLifecycleModuleInput {

    @Inject
    public SampleLifecycleModule() {
    }

    @Override
    public Fragment createFragment() {
        return SampleLifecycleFragment.newInstance();
    }
}