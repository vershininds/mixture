package com.vershininds.mixture.sample.presentation.modules.lifecycle.router;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.vershininds.mixture.router.AbstractRouter;
import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.presentation.modules.details.DetailsModuleInput;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.contract.SampleLifecycleRouterContract;
import com.vershininds.mixture.view.AndroidComponent;

import javax.inject.Inject;


public class SampleLifecycleRouter extends AbstractRouter<SampleLifecycleRouterContract.Presenter>
        implements SampleLifecycleRouterContract.Router {

    private DetailsModuleInput detailsModuleInput;

    @Inject
    public SampleLifecycleRouter(DetailsModuleInput detailsModuleInput) {
        this.detailsModuleInput = detailsModuleInput;
    }

    @Override
    public void showDetailsScreen(@NonNull AndroidComponent androidComponent, @NonNull SampleObject data) {
        Activity activity = androidComponent.getActivity();
        Intent intent = detailsModuleInput.createActivityIntent(activity, data);
        activity.startActivity(intent);
    }
}