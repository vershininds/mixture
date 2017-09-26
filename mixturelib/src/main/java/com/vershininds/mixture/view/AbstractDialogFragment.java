package com.vershininds.mixture.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import org.parceler.Parcels;

import com.vershininds.mixture.helper.PresenterStorage;
import com.vershininds.mixture.presenter.MvpPresenter;
import com.vershininds.mixture.viewmodel.MvpViewModel;

public abstract class AbstractDialogFragment<VM extends MvpViewModel, P extends MvpPresenter<VM>>
        extends DialogFragment implements AndroidComponent {

    private static final String VM_KEY = AbstractDialogFragment.class.getSimpleName() + "_VM";

    private P presenter;
    private VM viewModel;

    protected abstract void injectDi();
    protected abstract P createPresenter();

    protected P getPresenter() {
        return presenter;
    }
    protected VM getViewModel() {
        return viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDi();

        if (null == savedInstanceState) {
            presenter = createPresenter();
            viewModel = presenter.getViewModel();
        } else {
            viewModel = Parcels.unwrap(savedInstanceState.getParcelable(VM_KEY));
            presenter = PresenterStorage.getInstance().evict(getViewModel().getId());
            if (presenter == null) {
                presenter = createPresenter();
                if (viewModel != null) presenter.setViewModel(viewModel);
            } else {
                VM presenterVm = presenter.getViewModel();
                if (presenterVm != null) viewModel = presenterVm;
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        beforeViewAttached();
        presenter.attachView(this);
        afterViewAttached();
    }

    @Override
    public void onStop() {
        beforeViewDetached();
        presenter.detachView();
        afterViewDetached();

        super.onStop();
    }

    @Override
    public void onDestroy() {
        if (getActivity().isChangingConfigurations()) {
            PresenterStorage.getInstance().save(getViewModel().getId(), presenter);
        } else {
            presenter.destroy();
        }

        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(VM_KEY, Parcels.wrap(getViewModel()));

        super.onSaveInstanceState(outState);
    }


    @Override
    public FragmentManager getSupportFragmentManager() {
        return getFragmentManager();
    }

    protected void beforeViewAttached() {
    }

    protected void afterViewAttached() {
    }

    protected void beforeViewDetached() {
    }

    protected void afterViewDetached() {
    }
}