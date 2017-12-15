package com.example.viper.presentation.modules.rxinteractor;

import android.support.v4.app.Fragment;

import javax.inject.Inject;

import com.example.viper.presentation.modules.rxinteractor.view.SampleRxFragment;


public class SampleRxModule implements SampleRxModuleInput {

    @Inject
    public SampleRxModule() {
    }

    @Override
    public Fragment createFragment() {
        return SampleRxFragment.newInstance();
    }
}