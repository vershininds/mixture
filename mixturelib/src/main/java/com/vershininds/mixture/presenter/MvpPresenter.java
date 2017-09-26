package com.vershininds.mixture.presenter;


import com.vershininds.mixture.view.AndroidComponent;
import com.vershininds.mixture.viewmodel.MvpViewModel;

public interface MvpPresenter<VM extends MvpViewModel> {

    interface Action {
        void execute(AndroidComponent component);
    }

    void attachView(AndroidComponent component);
    void detachView();

    void setViewModel(VM viewModel);
    VM getViewModel();

    void destroy();
}