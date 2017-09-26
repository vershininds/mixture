package com.example.viper.presentation.modules.sample1.contract;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import com.example.viper.data.SampleObject;
import com.vershininds.mixture.interactor.MvpInteractor;


public interface Sample1InteractorContract {

    interface Presenter extends MvpInteractor.Listener {
        void onObtainedData(@NonNull List<SampleObject> data, @Nullable Throwable throwable);
    }

    interface Interactor extends MvpInteractor<Presenter> {
        void obtainData();
    }
}