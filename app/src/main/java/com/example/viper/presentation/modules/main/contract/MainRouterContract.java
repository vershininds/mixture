package com.example.viper.presentation.modules.main.contract;


import com.example.viper.data.SampleObject;
import com.vershininds.mixture.router.MvpRouter;
import com.vershininds.mixture.view.AndroidComponent;

public interface MainRouterContract {
    interface Presenter extends MvpRouter.Listener {
    }

    interface Router extends MvpRouter<Presenter> {
        void showSample1Screen(AndroidComponent androidComponent);
        void showSample2Screen(AndroidComponent androidComponent);
        void showDetailScreen(AndroidComponent androidComponent, SampleObject object);
    }
}