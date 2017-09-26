package com.example.viper.presentation.modules.sample2;

import android.support.v4.app.Fragment;

import javax.inject.Inject;

import com.example.viper.presentation.modules.sample2.view.Sample2Fragment;


public class Sample2Module implements Sample2ModuleInput {

    @Inject
    public Sample2Module() {
    }

    @Override
    public Fragment createFragment() {
        return Sample2Fragment.newInstance();
    }
}