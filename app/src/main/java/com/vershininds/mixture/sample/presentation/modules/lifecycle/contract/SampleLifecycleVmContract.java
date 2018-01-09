package com.vershininds.mixture.sample.presentation.modules.lifecycle.contract;


import android.databinding.Bindable;

import com.vershininds.mixture.presenter.MvpPresenter;
import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.viewmodel.MvpViewModel;

import java.util.List;


public interface SampleLifecycleVmContract {

    enum State {
        INITIAL,
        LOADING, EMPTY, DATA,
        ERROR
    }

    interface ViewModel extends MvpViewModel {
        @Bindable
        List<SampleObject> getData();
        void setData(List<SampleObject> data);

        @Bindable
        String getError();
        void setError(String error);

        @Bindable
        State getState();
        void setState(State state);
    }

    interface Presenter extends MvpPresenter<ViewModel> {
        void itemSelected(SampleObject item);
        void retry();
    }
}