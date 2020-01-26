package com.vershininds.mixture.sample.presentation;

import com.vershininds.mixture.sample.domain.assembly.BusinessComponent;
import com.vershininds.mixture.sample.presentation.modules.catalog.di.CatalogComponent;
import com.vershininds.mixture.sample.presentation.modules.details.di.DetailsComponent;
import com.vershininds.mixture.sample.presentation.modules.main.di.MainComponent;

import dagger.Component;

@PerPresentationLayerScope
@Component(modules = FeatureDeclaration.class, dependencies = BusinessComponent.class)
public interface PresentationComponents {
    MainComponent.Factory mainComponentFactory();
    DetailsComponent.Factory detailsComponentFactory();
    CatalogComponent.Factory catalogComponentFactory();
}
