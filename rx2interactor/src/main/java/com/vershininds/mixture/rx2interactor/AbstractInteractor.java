package com.vershininds.mixture.rx2interactor;


import android.support.annotation.CallSuper;

import com.vershininds.mixture.interactor.MvpInteractor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public abstract class AbstractInteractor<L extends MvpInteractor.Listener> implements MvpInteractor<L> {

    public final String TAG = this.getClass().getSimpleName();

    private CompositeDisposable cd = new CompositeDisposable();
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

    protected void cacheSubscription(Long id, Disposable disposable) {
        executedOperations.add(id);
        cd.add(disposable);
    }

    protected void clearSubscriptions() {
        executedOperations.clear();
        cd.dispose();
    }

    protected <T> void execute(Observable<T> observable, final ResultFactory<T, L> resultFactory) {
        final Long id = System.nanoTime();

        Disposable disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<T>() {
                    @Override
                    public void onNext(T t) {
                        resultFactory.create(t, null).deliver(getListener());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        markOperationFinished(id);
                        resultFactory.create(null, e).deliver(getListener());
                    }

                    @Override
                    public void onComplete() {
                        markOperationFinished(id);
                    }
                });

        cacheSubscription(id, disposable);
    }

    protected <T> void execute(Observable<T> observable, final Callback<T, L> callback) {
        final Long id = System.nanoTime();

        Disposable disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<T>() {
                    @Override
                    public void onNext(T t) {
                        callback.onResult(t, getListener());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        markOperationFinished(id);
                        callback.onError(e, getListener());
                    }

                    @Override
                    public void onComplete() {
                        markOperationFinished(id);
                    }
                });

        cacheSubscription(id, disposable);
    }

    protected void cancelPreviousRequests() {
        clearSubscriptions();
        recreateCompositeSubscription();
    }

    private void recreateCompositeSubscription() {
        if (cd.isDisposed()) {
            cd = new CompositeDisposable();
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