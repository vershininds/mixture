package com.example.viper.presentation.modules.sample1;

import android.support.v4.app.Fragment;

import javax.inject.Inject;

import com.example.viper.presentation.modules.sample1.view.Sample1Fragment;


public class Sample1Module implements Sample1ModuleInput {

    @Inject
    public Sample1Module() {
    }

    @Override
    public Fragment createFragment() {
        return Sample1Fragment.newInstance();
    }
}