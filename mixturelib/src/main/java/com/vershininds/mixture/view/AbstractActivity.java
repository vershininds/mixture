package com.vershininds.mixture.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.parceler.Parcels;

import com.vershininds.mixture.helper.PresenterStorage;
import com.vershininds.mixture.presenter.MvpPresenter;
import com.vershininds.mixture.viewmodel.MvpViewModel;


public abstract class AbstractActivity<VM extends MvpViewModel, P extends MvpPresenter<VM>>
        extends AppCompatActivity implements AndroidComponent {

    private static final String VM_KEY = AbstractActivity.class.getSimpleName() + "_VM";

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
                if(viewModel != null) presenter.setViewModel(viewModel);
            } else {
                VM presenterVm = presenter.getViewModel();
                if (presenterVm != null) viewModel = presenterVm;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        beforeViewAttached();
        presenter.attachView(this);
        afterViewAttached();
    }

    @Override
    protected void onStop() {
        beforeViewDetached();
        presenter.detachView();
        afterViewDetached();

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (isChangingConfigurations()) {
            PresenterStorage.getInstance().save(getViewModel().getId(), presenter);
        } else {
            presenter.destroy();
        }

        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(VM_KEY, Parcels.wrap(getViewModel()));

        super.onSaveInstanceState(outState);
    }

    @Override
    public Activity getActivity() {
        return this;
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
