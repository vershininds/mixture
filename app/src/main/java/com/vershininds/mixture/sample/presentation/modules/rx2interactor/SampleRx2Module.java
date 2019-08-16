package com.vershininds.mixture.sample.presentation.modules.rx2interactor;

import android.support.v4.app.Fragment;

import com.vershininds.mixture.sample.presentation.modules.rx2interactor.view.SampleRx2Fragment;

import javax.inject.Inject;


public class SampleRx2Module implements SampleRx2ModuleContract {

    @Inject
    public SampleRx2Module() {
    }

    @Override
    public Fragment createFragment() {
        return SampleRx2Fragment.Companion.newInstance();
    }
}