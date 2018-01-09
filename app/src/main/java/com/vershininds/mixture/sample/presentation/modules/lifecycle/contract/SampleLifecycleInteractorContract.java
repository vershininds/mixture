package com.vershininds.mixture.sample.presentation.modules.lifecycle.contract;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vershininds.mixture.interactor.MvpInteractor;
import com.vershininds.mixture.sample.data.SampleObject;

import java.util.List;


public interface SampleLifecycleInteractorContract {

    interface Presenter extends MvpInteractor.Listener {
        void onObtainedData(@NonNull List<SampleObject> data, @Nullable Throwable throwable);
    }

    interface Interactor extends MvpInteractor<Presenter> {
        void obtainData();
    }
}