package com.example.viper.presentation;

import dagger.Component;
import com.example.viper.domain.assembly.BusinessComponent;
import com.example.viper.presentation.modules.details.assembly.DetailsComponent;
import com.example.viper.presentation.modules.details.assembly.DetailsDaggerModule;
import com.example.viper.presentation.modules.main.assembly.MainComponent;
import com.example.viper.presentation.modules.main.assembly.MainDaggerModule;
import com.example.viper.presentation.modules.rxinteractor.assembly.SampleRxComponent;
import com.example.viper.presentation.modules.rxinteractor.assembly.SampleRxDaggerModule;
import com.example.viper.presentation.modules.rx2interactor.assembly.SampleRx2Component;
import com.example.viper.presentation.modules.rx2interactor.assembly.SampleRx2DaggerModule;

@PerPresentationLayerScope
@Component(modules = FeatureDeclaration.class, dependencies = BusinessComponent.class)
public interface PresentationComponents {
    MainComponent mainComponent(MainDaggerModule mainDaggerModule);
    DetailsComponent detailsComponent(DetailsDaggerModule detailsDaggerModule);

    SampleRxComponent sample1Component(SampleRxDaggerModule sampleRxDaggerModule);
    SampleRx2Component sample2Component(SampleRx2DaggerModule sampleRx2DaggerModule);
}
