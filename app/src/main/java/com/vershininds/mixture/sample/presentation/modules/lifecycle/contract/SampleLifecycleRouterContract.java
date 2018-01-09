package com.vershininds.mixture.sample.presentation.modules.lifecycle.contract;


import android.support.annotation.NonNull;

import com.vershininds.mixture.router.MvpRouter;
import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.view.AndroidComponent;


public interface SampleLifecycleRouterContract {
    interface Presenter extends MvpRouter.Listener {
    }

    interface Router extends MvpRouter<Presenter> {
        void showDetailsScreen(@NonNull AndroidComponent androidComponent, @NonNull SampleObject data);
    }
}