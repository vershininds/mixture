package com.example.viper.presentation.modules.details;

import android.app.Activity;
import android.content.Intent;

import javax.inject.Inject;

import com.example.viper.data.SampleObject;
import com.example.viper.presentation.modules.details.view.DetailsActivity;


public class DetailsModule implements DetailsModuleInput {

    @Inject
    public DetailsModule() {
    }

    @Override
    public Intent createActivityIntent(Activity source, SampleObject data) {
        return DetailsActivity.createIntent(source, data);
    }
}