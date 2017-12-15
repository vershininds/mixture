package com.vershininds.mixture.sample.presentation.modules.details.contract;


import android.databinding.Bindable;

import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.presenter.MvpPresenter;
import com.vershininds.mixture.viewmodel.MvpViewModel;


public interface DetailsVmContract {

    enum State {
        INITIAL,
        LOADING, EMPTY, DATA,
        ERROR
    }

    interface ViewModel extends MvpViewModel {
        @Bindable
        SampleObject getData();
        void setData(SampleObject data);

        String getSomeInformation();

        @Bindable
        String getError();
        void setError(String error);

        @Bindable
        State getState();
        void setState(State state);
    }

    interface Presenter extends MvpPresenter<ViewModel> {
        void onClickFinish();
    }
}