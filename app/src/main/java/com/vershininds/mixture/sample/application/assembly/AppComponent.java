package com.vershininds.mixture.sample.application.assembly;

import android.content.Context;

import dagger.Component;
import com.vershininds.mixture.sample.application.AppDelegate;
import com.vershininds.mixture.sample.presentation.PresentationComponents;


@PerApplicationLayerScope
@Component(modules = {AppModule.class},
        dependencies = PresentationComponents.class)
public interface AppComponent {
    void inject(AppDelegate applicationDelegate);

    Context context();
}
