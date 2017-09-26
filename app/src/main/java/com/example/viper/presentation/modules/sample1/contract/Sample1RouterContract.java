package com.example.viper.presentation.modules.sample1.contract;


import android.support.annotation.NonNull;

import com.example.viper.data.SampleObject;
import com.vershininds.mixture.router.MvpRouter;
import com.vershininds.mixture.view.AndroidComponent;


public interface Sample1RouterContract {
    interface Presenter extends MvpRouter.Listener {
    }

    interface Router extends MvpRouter<Presenter> {
        void showDetailsScreen(@NonNull AndroidComponent androidComponent, @NonNull SampleObject data);
    }
}