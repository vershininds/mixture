package com.example.viper.presentation.modules.details.router;

import android.app.Activity;

import javax.inject.Inject;

import com.example.viper.presentation.modules.details.contract.DetailsRouterContract;
import com.vershininds.mixture.router.AbstractRouter;
import com.vershininds.mixture.view.AndroidComponent;


public class DetailsRouter extends AbstractRouter<DetailsRouterContract.Presenter>
        implements DetailsRouterContract.Router {

    @Inject
    public DetailsRouter() {
    }

    @Override
    public void finishScreen(AndroidComponent androidComponent) {
        if (androidComponent != null) {
            Activity activity = androidComponent.getActivity();
            activity.finish();
        }
    }
}