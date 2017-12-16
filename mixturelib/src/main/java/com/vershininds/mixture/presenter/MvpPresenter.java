package com.vershininds.mixture.presenter;


import com.vershininds.mixture.view.AndroidComponent;
import com.vershininds.mixture.viewmodel.MvpViewModel;
import com.vershininds.mixture.router.MvpRouter;
import com.vershininds.mixture.interactor.MvpInteractor;

/**
 * Base presenter interface.
 * Presenter receives information about the user's actions from View and transforms it into requests
 * to Router{@link MvpRouter},
 * Interactor{@link MvpInteractor}.
 * Receives data from Interactor, prepares them and set in ViewModel.
 * Then ViewModel post data in view to display.
 * @param <VM> {@link MvpViewModel}
 */
public interface MvpPresenter<VM extends MvpViewModel> {

    interface Action {
        void execute(AndroidComponent component);
    }

    /**
     * Attach view to presenter
     * @param component {@link AndroidComponent}
     */
    void attachView(AndroidComponent component);

    /**
     * Detach view from presenter when view destroyed(activity or fragment recreate)
     */
    void detachView();

    /**
     * Set last actual ViewModel to presenter
     * @param viewModel {@link MvpViewModel}
     */
    void setViewModel(VM viewModel);

    /**
     * Get last actual ViewModel
     * @return viewModel {@link MvpViewModel}
     */
    VM getViewModel();

    void destroy();
}