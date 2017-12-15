package com.vershininds.mixture.sample.presentation.modules.rxinteractor;

import android.support.v4.app.Fragment;

import javax.inject.Inject;

import com.vershininds.mixture.sample.presentation.modules.rxinteractor.view.SampleRxFragment;


public class SampleRxModule implements SampleRxModuleInput {

    @Inject
    public SampleRxModule() {
    }

    @Override
    public Fragment createFragment() {
        return SampleRxFragment.newInstance();
    }
}