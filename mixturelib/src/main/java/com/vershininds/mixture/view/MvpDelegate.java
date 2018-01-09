package com.vershininds.mixture.view;


import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.vershininds.mixture.helper.PresenterStorage;
import com.vershininds.mixture.presenter.MvpPresenter;
import com.vershininds.mixture.viewmodel.MvpViewModel;

import org.parceler.Parcels;

public abstract class MvpDelegate<VM extends MvpViewModel, P extends MvpPresenter<VM>> implements LifecycleObserver {

    private static final String VM_KEY = AbstractActivity.class.getSimpleName() + "_VM";

    private Lifecycle lifecycle;
    private AndroidComponent component;
    private Observable.OnPropertyChangedCallback vmObserver;

    private P presenter;
    private VM viewModel;

    protected abstract void injectDi();
    protected abstract P createPresenter();

    public MvpDelegate(@NonNull Lifecycle lifecycle, @NonNull AndroidComponent component) {
        this.lifecycle = lifecycle;
        this.component = component;

        injectDi();

        presenter = createPresenter();
        viewModel = presenter.getViewModel();

        this.lifecycle.addObserver(this);
    }

    public MvpDelegate(@NonNull Lifecycle lifecycle, @NonNull AndroidComponent component, @NonNull Observable.OnPropertyChangedCallback vmObserver) {
        this(lifecycle, component);
        this.vmObserver = vmObserver;
    }

    public MvpDelegate(@NonNull Lifecycle lifecycle, @NonNull AppCompatActivity activity) {
        this(lifecycle, new AndroidComponent() {
            @Override
            public Activity getActivity() {
                return activity;
            }

            @Override
            public FragmentManager getSupportFragmentManager() {
                return activity.getSupportFragmentManager();
            }
        });
    }

    public MvpDelegate(@NonNull Lifecycle lifecycle, @NonNull Fragment fragment) {
        this(lifecycle, new AndroidComponent() {
            @Override
            public Activity getActivity() {
                return fragment.getActivity();
            }

            @Override
            public FragmentManager getSupportFragmentManager() {
                return fragment.getFragmentManager();
            }
        });
    }

    public P getPresenter() {
        return presenter;
    }

    public VM getViewModel() {
        return viewModel;
    }

    public Bundle saveInstanceState(Bundle outState) {
        outState.putParcelable(VM_KEY, Parcels.wrap(viewModel));
        return outState;
    }

    public void restoreInstanceState(@Nullable Bundle savedInstanceState){
        if (savedInstanceState == null) return;

        viewModel = Parcels.unwrap(savedInstanceState.getParcelable(VM_KEY));
        presenter = PresenterStorage.getInstance().evict(viewModel.getId());
        if (presenter == null) {
            presenter = createPresenter();
            if(viewModel != null) presenter.setViewModel(viewModel);
        } else {
            VM presenterVm = presenter.getViewModel();
            if (presenterVm != null) viewModel = presenterVm;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void start() {
        beforeViewAttached();
        if(presenter != null) presenter.attachView(component);
        afterViewAttached();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void stop() {
        beforeViewDetached();
        if(presenter != null) presenter.detachView();
        afterViewDetached();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void destroy() {
        if (component != null){
            if (component.getActivity().isChangingConfigurations()) {
                PresenterStorage.getInstance().save(viewModel.getId(), presenter);
            } else {
                presenter.destroy();
            }

            component = null;
        }
        if(vmObserver != null) vmObserver = null;
        if(lifecycle != null) lifecycle.removeObserver(this);
    }

    protected void beforeViewAttached() {
        if(vmObserver != null) {
            viewModel.addOnPropertyChangedCallback(vmObserver);
        }
    }

    protected void afterViewAttached() {
    }

    protected void beforeViewDetached() {
    }

    protected void afterViewDetached() {
        if(vmObserver != null) {
            viewModel.removeOnPropertyChangedCallback(vmObserver);
        }
    }
}
