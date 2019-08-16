package com.vershininds.mixture.sample.presentation;

import com.vershininds.mixture.sample.presentation.modules.details.DetailsModule;
import com.vershininds.mixture.sample.presentation.modules.details.DetailsModuleContract;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.SampleRx2Module;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.SampleRx2ModuleContract;

import dagger.Module;
import dagger.Provides;

@Module
public class FeatureDeclaration {

    @PerPresentationLayerScope
    @Provides
    public DetailsModuleContract providesDetailsModule(DetailsModule detailsModule) {
        return detailsModule;
    }

    @PerPresentationLayerScope
    @Provides
    public SampleRx2ModuleContract providesSample2Module(SampleRx2Module sampleRx2Module) {
        return sampleRx2Module;
    }
}
