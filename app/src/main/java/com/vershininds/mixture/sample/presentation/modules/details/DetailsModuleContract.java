package com.vershininds.mixture.sample.presentation.modules.details;

import android.app.Activity;
import android.content.Intent;

import com.vershininds.mixture.sample.data.SampleObject;


public interface DetailsModuleContract {
    Intent createActivityIntent(Activity source, SampleObject data);

    class DetailsFinishedEvent {
        public final SampleObject data;

        public DetailsFinishedEvent(SampleObject data) {
            this.data = data;
        }
    }
}