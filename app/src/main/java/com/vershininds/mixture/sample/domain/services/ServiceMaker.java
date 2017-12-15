package com.vershininds.mixture.sample.domain.services;

import javax.inject.Inject;

import dagger.Lazy;
import com.vershininds.mixture.sample.application.AppDelegate;

public class ServiceMaker {

    @Inject
    protected Lazy<DataService> dataService;

    public ServiceMaker(){
        AppDelegate.get().businessComponent().inject(this);
    }

    public DataService getDataService() {
        return dataService.get();
    }
}
