package com.vershininds.mixture.sample.presentation;

import dagger.Component;

import com.vershininds.mixture.sample.presentation.modules.details.assembly.DetailsDaggerModule;
import com.vershininds.mixture.sample.presentation.modules.main.assembly.MainComponent;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.assembly.SampleRx2DaggerModule;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.assembly.SampleRxComponent;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.assembly.SampleRxDaggerModule;
import com.vershininds.mixture.sample.domain.assembly.BusinessComponent;
import com.vershininds.mixture.sample.presentation.modules.details.assembly.DetailsComponent;
import com.vershininds.mixture.sample.presentation.modules.main.assembly.MainDaggerModule;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.assembly.SampleRx2Component;

@PerPresentationLayerScope
@Component(modules = FeatureDeclaration.class, dependencies = BusinessComponent.class)
public interface PresentationComponents {
    MainComponent mainComponent(MainDaggerModule mainDaggerModule);
    DetailsComponent detailsComponent(DetailsDaggerModule detailsDaggerModule);

    SampleRxComponent sample1Component(SampleRxDaggerModule sampleRxDaggerModule);
    SampleRx2Component sample2Component(SampleRx2DaggerModule sampleRx2DaggerModule);
}
