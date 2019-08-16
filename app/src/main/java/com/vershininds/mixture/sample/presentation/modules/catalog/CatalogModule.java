package com.vershininds.mixture.sample.presentation.modules.catalog;

import android.support.v4.app.Fragment;

import com.vershininds.mixture.sample.presentation.modules.catalog.view.CatalogFragment;

import javax.inject.Inject;


public class CatalogModule implements CatalogModuleContract {

    @Inject
    public CatalogModule() {
    }

    @Override
    public Fragment createFragment() {
        return CatalogFragment.Companion.newInstance();
    }
}