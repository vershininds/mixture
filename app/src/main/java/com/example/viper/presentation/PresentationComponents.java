package com.example.viper.presentation;

import dagger.Component;
import com.example.viper.domain.assembly.BusinessComponent;
import com.example.viper.presentation.modules.details.assembly.DetailsComponent;
import com.example.viper.presentation.modules.details.assembly.DetailsDaggerModule;
import com.example.viper.presentation.modules.main.assembly.MainComponent;
import com.example.viper.presentation.modules.main.assembly.MainDaggerModule;
import com.example.viper.presentation.modules.sample1.assembly.Sample1Component;
import com.example.viper.presentation.modules.sample1.assembly.Sample1DaggerModule;
import com.example.viper.presentation.modules.sample2.assembly.Sample2Component;
import com.example.viper.presentation.modules.sample2.assembly.Sample2DaggerModule;

@PerPresentationLayerScope
@Component(modules = FeatureDeclaration.class, dependencies = BusinessComponent.class)
public interface PresentationComponents {
    MainComponent mainComponent(MainDaggerModule mainDaggerModule);
    DetailsComponent detailsComponent(DetailsDaggerModule detailsDaggerModule);

    Sample1Component sample1Component(Sample1DaggerModule sample1DaggerModule);
    Sample2Component sample2Component(Sample2DaggerModule sample2DaggerModule);
}
