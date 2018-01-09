package com.vershininds.mixture.sample.presentation;

import com.vershininds.mixture.sample.presentation.modules.details.DetailsModule;
import com.vershininds.mixture.sample.presentation.modules.details.DetailsModuleInput;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.SampleLifecycleModule;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.SampleLifecycleModuleInput;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.SampleRx2Module;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.SampleRx2ModuleInput;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.SampleRxModule;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.SampleRxModuleInput;

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
    public SampleRxModuleInput providesSample1Module(SampleRxModule sampleRxModule) {
        return sampleRxModule;
    }

    @PerPresentationLayerScope
    @Provides
    public SampleRx2ModuleInput providesSample2Module(SampleRx2Module sampleRx2Module) {
        return sampleRx2Module;
    }

    @PerPresentationLayerScope
    @Provides
    public SampleLifecycleModuleInput providesSampleLifecycleModule(SampleLifecycleModule sampleLifecycleModule) {
        return sampleLifecycleModule;
    }
}
