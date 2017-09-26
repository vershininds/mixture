package com.example.viper.presentation.modules.details.contract;


import com.vershininds.mixture.interactor.MvpInteractor;

public interface DetailsInteractorContract {

    interface Presenter extends MvpInteractor.Listener {
//        void onObtainedData(@Nullable SampleObject data, @Nullable Throwable throwable);
    }

    interface Interactor extends MvpInteractor<Presenter> {
//        void obtainData();
    }
}