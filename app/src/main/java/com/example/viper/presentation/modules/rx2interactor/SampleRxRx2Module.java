package com.example.viper.presentation.modules.rx2interactor;

import android.support.v4.app.Fragment;

import javax.inject.Inject;

import com.example.viper.presentation.modules.rx2interactor.view.SampleRx2Fragment;


public class SampleRxRx2Module implements SampleRx2ModuleInput {

    @Inject
    public SampleRxRx2Module() {
    }

    @Override
    public Fragment createFragment() {
        return SampleRx2Fragment.newInstance();
    }
}