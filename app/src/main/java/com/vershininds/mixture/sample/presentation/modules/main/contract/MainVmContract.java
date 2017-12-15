package com.vershininds.mixture.sample.presentation.modules.main.contract;


import android.databinding.Bindable;

import com.vershininds.mixture.presenter.MvpPresenter;
import com.vershininds.mixture.viewmodel.MvpViewModel;


public interface MainVmContract {

    enum State {
        INITIAL,
        SAMPLE_1,
        SAMPLE_2
    }

    interface ViewModel extends MvpViewModel {
        @Bindable
        State getState();
        void setState(State state);
    }

    interface Presenter extends MvpPresenter<ViewModel> {
        void showSample1();
        void showSample2();
    }
}