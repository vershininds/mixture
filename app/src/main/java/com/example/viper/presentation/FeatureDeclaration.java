package com.example.viper.presentation;

import dagger.Module;
import dagger.Provides;
import com.example.viper.presentation.modules.details.DetailsModule;
import com.example.viper.presentation.modules.details.DetailsModuleInput;
import com.example.viper.presentation.modules.sample1.Sample1Module;
import com.example.viper.presentation.modules.sample1.Sample1ModuleInput;
import com.example.viper.presentation.modules.sample2.Sample2Module;
import com.example.viper.presentation.modules.sample2.Sample2ModuleInput;

@Module
public class FeatureDeclaration {

    @PerPresentationLayerScope
    @Provides
    public DetailsModuleInput providesDetailsModule(DetailsModule detailsModule) {
        return detailsModule;
    }

    @PerPresentationLayerScope
    @Provides
    public Sample1ModuleInput providesSample1Module(Sample1Module sample1Module) {
        return sample1Module;
    }

    @PerPresentationLayerScope
    @Provides
    public Sample2ModuleInput providesSample2Module(Sample2Module sample2Module) {
        return sample2Module;
    }
}
