package com.example.viper.domain.services;

import javax.inject.Inject;

import dagger.Lazy;
import com.example.viper.application.AppDelegate;

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
