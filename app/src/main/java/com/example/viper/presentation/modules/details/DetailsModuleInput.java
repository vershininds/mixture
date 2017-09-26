package com.example.viper.presentation.modules.details;

import android.app.Activity;
import android.content.Intent;

import com.example.viper.data.SampleObject;


public interface DetailsModuleInput {
    Intent createActivityIntent(Activity source, SampleObject data);

    class DetailsFinishedEvent {
        public final SampleObject data;

        public DetailsFinishedEvent(SampleObject data) {
            this.data = data;
        }
    }
}