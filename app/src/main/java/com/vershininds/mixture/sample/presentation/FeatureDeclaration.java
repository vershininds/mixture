package com.vershininds.mixture.sample.presentation;

import com.vershininds.mixture.sample.presentation.modules.details.DetailsModule;
import com.vershininds.mixture.sample.presentation.modules.details.DetailsModuleInput;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.SampleRx2ModuleInput;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.SampleRxRx2Module;

import dagger.Module;
import dagger.Provides;

@Module
public class FeatureDeclaration {

    @PerPresentationLayerScope
    @Provides
    public DetailsModuleInput providesDetailsModule(DetailsModule detailsModule) {
        return detailsModule;
    }

    @PerPresentationLayerScope
    @Provides
    public SampleRx2ModuleInput providesSample2Module(SampleRxRx2Module sampleRx2Module) {
        return sampleRx2Module;
    }
}
