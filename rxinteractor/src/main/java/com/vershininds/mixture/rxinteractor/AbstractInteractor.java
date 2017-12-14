package com.vershininds.mixture.rxinteractor;


import android.support.annotation.CallSuper;

import java.util.ArrayList;
import java.util.List;

import com.vershininds.mixture.interactor.MvpInteractor;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public abstract class AbstractInteractor <L extends MvpInteractor.Listener> implements MvpInteractor<L> {

    public final String TAG = this.getClass().getSimpleName();

    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    private List<Long> executedOperations = new ArrayList<>();
    private L listener;

    @Override
    public State getState() {
        return executedOperations.isEmpty() ? State.IDLE : State.WORKING;
    }

    @CallSuper
    @Override
    public void setListener(L listener) {
        this.listener = listener;
        recreateCompositeSubscription();
    }

    @CallSuper
    @Override
    public void destroy() {
        clearSubscriptions();
    }

    @Override
    public void reset() {
        cancelPreviousRequests();
    }

    protected L getListener() {
        return listener;
    }

    protected void cacheSubscription(Long id, Subscription subscription) {
        executedOperations.add(id);
        compositeSubscription.add(subscription);
    }

    protected void clearSubscriptions() {
        executedOperations.clear();
        compositeSubscription.unsubscribe();
    }

    protected <T> void execute(Observable<T> observable, final ResultFactory<T, L> resultFactory) {
        final Long id = System.nanoTime();

        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        markOperationFinished(id);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        markOperationFinished(id);
                        resultFactory.create(null, e).deliver(getListener());
                    }

                    @Override
                    public void onNext(T data) {
                        resultFactory.create(data, null).deliver(getListener());
                    }
                });

        cacheSubscription(id, subscription);
    }

    protected <T> void execute(Observable<T> observable, final Callback<T, L> callback) {
        final Long id = System.nanoTime();

        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        markOperationFinished(id);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        markOperationFinished(id);
                        callback.onError(e, getListener());
                    }

                    @Override
                    public void onNext(T data) {
                        callback.onResult(data, getListener());
                    }
                });

        cacheSubscription(id, subscription);
    }

    protected void cancelPreviousRequests() {
        clearSubscriptions();
        recreateCompositeSubscription();
    }

    private void recreateCompositeSubscription() {
        if (compositeSubscription.isUnsubscribed()) {
            compositeSubscription = new CompositeSubscription();
        }
    }

    private void markOperationFinished(Long taskId) {
        executedOperations.remove(taskId);
    }

    protected interface ResultFactory<T, L> {
        PendingResult<T, L> create(T data, Throwable throwable);
    }

    protected interface Callback<T, L> {
        void onResult(T data, L target);
        void onError(Throwable throwable, L target);
    }
}