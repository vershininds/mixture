package com.vershininds.mixture.sample.presentation.modules.details;

import android.app.Activity;
import android.content.Intent;

import javax.inject.Inject;

import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.presentation.modules.details.view.DetailsActivity;


public class DetailsModule implements DetailsModuleContract {

    @Inject
    public DetailsModule() {
    }

    @Override
    public Intent createActivityIntent(Activity source, SampleObject data) {
        return DetailsActivity.Companion.createIntent(source, data);
    }
}