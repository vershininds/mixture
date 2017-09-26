package com.example.viper.presentation.modules.details.contract;


import com.vershininds.mixture.router.MvpRouter;
import com.vershininds.mixture.view.AndroidComponent;

public interface DetailsRouterContract {
    interface Presenter extends MvpRouter.Listener {
    }

    interface Router extends MvpRouter<Presenter> {
        void finishScreen(AndroidComponent androidComponent);
    }
}