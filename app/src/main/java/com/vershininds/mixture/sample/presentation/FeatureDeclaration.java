package com.vershininds.mixture.sample.presentation;

import com.vershininds.mixture.sample.presentation.modules.details.DetailsModule;
import com.vershininds.mixture.sample.presentation.modules.details.DetailsModuleContract;
import com.vershininds.mixture.sample.presentation.modules.catalog.CatalogModule;
import com.vershininds.mixture.sample.presentation.modules.catalog.CatalogModuleContract;

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
    public CatalogModuleContract providesCatalogModule(CatalogModule catalogModule) {
        return catalogModule;
    }
}
