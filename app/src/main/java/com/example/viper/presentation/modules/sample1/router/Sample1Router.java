package com.example.viper.presentation.modules.sample1.router;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import com.example.viper.data.SampleObject;
import com.example.viper.presentation.modules.details.DetailsModuleInput;
import com.example.viper.presentation.modules.sample1.contract.Sample1RouterContract;
import com.vershininds.mixture.router.AbstractRouter;
import com.vershininds.mixture.view.AndroidComponent;


public class Sample1Router extends AbstractRouter<Sample1RouterContract.Presenter>
        implements Sample1RouterContract.Router {

    private DetailsModuleInput detailsModuleInput;

    @Inject
    public Sample1Router(DetailsModuleInput detailsModuleInput) {
        this.detailsModuleInput = detailsModuleInput;
    }

    @Override
    public void showDetailsScreen(@NonNull AndroidComponent androidComponent, @NonNull SampleObject data) {
        Activity activity = androidComponent.getActivity();
        Intent intent = detailsModuleInput.createActivityIntent(activity, data);
        activity.startActivity(intent);
    }
}