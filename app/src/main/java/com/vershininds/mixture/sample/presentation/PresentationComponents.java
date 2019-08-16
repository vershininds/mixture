package com.vershininds.mixture.sample.presentation;

import com.vershininds.mixture.sample.domain.assembly.BusinessComponent;
import com.vershininds.mixture.sample.presentation.modules.details.di.DetailsComponent;
import com.vershininds.mixture.sample.presentation.modules.details.di.DetailsDiModule;
import com.vershininds.mixture.sample.presentation.modules.main.di.MainComponent;
import com.vershininds.mixture.sample.presentation.modules.main.di.MainDiModule;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.di.SampleRx2Component;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.di.SampleRx2DiModule;

import dagger.Component;

@PerPresentationLayerScope
@Component(modules = FeatureDeclaration.class, dependencies = BusinessComponent.class)
public interface PresentationComponents {
    MainComponent mainComponent(MainDiModule mainDiModule);
    DetailsComponent detailsComponent(DetailsDiModule detailsDiModule);

    SampleRx2Component sample2Component(SampleRx2DiModule sampleRx2DiModule);
}
