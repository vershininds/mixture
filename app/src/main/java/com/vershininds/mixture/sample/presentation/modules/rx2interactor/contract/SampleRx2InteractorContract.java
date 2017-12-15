package com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.interactor.MvpInteractor;


public interface SampleRx2InteractorContract {

    interface Presenter extends MvpInteractor.Listener {
        void onObtainedData(@NonNull List<SampleObject> data, @Nullable Throwable throwable);
    }

    interface Interactor extends MvpInteractor<Presenter> {
        void obtainData();
    }
}