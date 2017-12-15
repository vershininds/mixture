package com.example.viper.presentation.modules.rxinteractor.router;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import com.example.viper.data.SampleObject;
import com.example.viper.presentation.modules.details.DetailsModuleInput;
import com.example.viper.presentation.modules.rxinteractor.contract.SampleRxRouterContract;
import com.vershininds.mixture.router.AbstractRouter;
import com.vershininds.mixture.view.AndroidComponent;


public class SampleRxRouter extends AbstractRouter<SampleRxRouterContract.Presenter>
        implements SampleRxRouterContract.Router {

    private DetailsModuleInput detailsModuleInput;

    @Inject
    public SampleRxRouter(DetailsModuleInput detailsModuleInput) {
        this.detailsModuleInput = detailsModuleInput;
    }

    @Override
    public void showDetailsScreen(@NonNull AndroidComponent androidComponent, @NonNull SampleObject data) {
        Activity activity = androidComponent.getActivity();
        Intent intent = detailsModuleInput.createActivityIntent(activity, data);
        activity.startActivity(intent);
    }
}