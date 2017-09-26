package com.example.viper.application.assembly;

import android.content.Context;

import dagger.Component;
import com.example.viper.application.AppDelegate;
import com.example.viper.presentation.PresentationComponents;


@PerApplicationLayerScope
@Component(modules = {AppModule.class},
        dependencies = PresentationComponents.class)
public interface AppComponent {
    void inject(AppDelegate applicationDelegate);

    Context context();
}
