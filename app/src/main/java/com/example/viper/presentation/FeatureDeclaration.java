package com.example.viper.presentation;

import dagger.Module;
import dagger.Provides;
import com.example.viper.presentation.modules.details.DetailsModule;
import com.example.viper.presentation.modules.details.DetailsModuleInput;
import com.example.viper.presentation.modules.rxinteractor.SampleRxModule;
import com.example.viper.presentation.modules.rxinteractor.SampleRxModuleInput;
import com.example.viper.presentation.modules.rx2interactor.SampleRxRx2Module;
import com.example.viper.presentation.modules.rx2interactor.SampleRx2ModuleInput;

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
    public SampleRx2ModuleInput providesSample2Module(SampleRxRx2Module sampleRx2Module) {
        return sampleRx2Module;
    }
}
