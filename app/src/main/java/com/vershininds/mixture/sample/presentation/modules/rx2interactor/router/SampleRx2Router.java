package com.vershininds.mixture.sample.presentation.modules.rx2interactor.router;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.presentation.modules.details.DetailsModuleInput;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2RouterContract;
import com.vershininds.mixture.router.AbstractRouter;
import com.vershininds.mixture.view.AndroidComponent;


public class SampleRx2Router extends AbstractRouter<SampleRx2RouterContract.Presenter>
        implements SampleRx2RouterContract.Router {

    private DetailsModuleInput detailsModuleInput;

    @Inject
    public SampleRx2Router(DetailsModuleInput detailsModuleInput) {
        this.detailsModuleInput = detailsModuleInput;
    }

    @Override
    public void showDetailsScreen(@NonNull AndroidComponent androidComponent, @NonNull SampleObject data) {
        Activity activity = androidComponent.getActivity();
        Intent intent = detailsModuleInput.createActivityIntent(activity, data);
        activity.startActivity(intent);
    }
}