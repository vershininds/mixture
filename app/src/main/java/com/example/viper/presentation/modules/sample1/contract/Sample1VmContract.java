package com.example.viper.presentation.modules.sample1.contract;


import android.databinding.Bindable;

import java.util.List;

import com.example.viper.data.SampleObject;
import com.vershininds.mixture.presenter.MvpPresenter;
import com.vershininds.mixture.viewmodel.MvpViewModel;


public interface Sample1VmContract {

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