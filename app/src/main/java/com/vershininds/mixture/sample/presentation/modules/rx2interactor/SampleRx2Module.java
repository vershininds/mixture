package com.vershininds.mixture.sample.presentation.modules.rx2interactor;

import android.support.v4.app.Fragment;

import javax.inject.Inject;

import com.vershininds.mixture.sample.presentation.modules.rx2interactor.view.SampleRx2Fragment;


public class SampleRx2Module implements SampleRx2ModuleInput {

    @Inject
    public SampleRx2Module() {
    }

    @Override
    public Fragment createFragment() {
        return SampleRx2Fragment.newInstance();
    }
}