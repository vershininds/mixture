package com.vershininds.mixture.presenter;

import android.support.annotation.CallSuper;

import com.vershininds.mixture.view.AndroidComponent;
import com.vershininds.mixture.viewmodel.MvpViewModel;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Presenter with base functionality
 * @param <VM> {@link MvpViewModel}
 */
public abstract class AbstractPresenter<VM extends MvpViewModel>
        implements MvpPresenter<VM> {

    private final String TAG = AbstractPresenter.this.getClass().getSimpleName();
    private final Queue<Action> pendingActions = new LinkedList<>();

    private VM viewModel;
    private AndroidComponent component;

    public AbstractPresenter(VM viewModel) {
        this.viewModel = viewModel;
    }

    @CallSuper
    @Override
    public void attachView(AndroidComponent component) {
        this.component = component;
        applyPendingActions();
    }

    @CallSuper
    @Override
    public void detachView() {
        this.component = null;
    }

    @CallSuper
    @Override
    public void destroy() {
        pendingActions.clear();
    }

    @Override
    public void setViewModel(VM viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public VM getViewModel() {
        return viewModel;
    }

    protected AndroidComponent getAndroidComponent() {
        return component;
    }

    /**
     * This method checks the view availability.
     * Perform an action on the view if view available.
     * Else the action will be cached and executed after view attached.
     * @param action {@link Action}
     */
    protected void applyAction(Action action) {
        AndroidComponent component = getAndroidComponent();
        if (component != null) {
            try {
                action.execute(component);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                pendingActions.add(action);
            }
        } else {
            pendingActions.add(action);
        }
    }

    private void applyPendingActions() {
        AndroidComponent component = getAndroidComponent();
        while (!pendingActions.isEmpty()) {
            Action action = pendingActions.peek();
            try {
                action.execute(component);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }

            pendingActions.remove();//clean action always after apply attempt to avoid loops
        }
    }
}