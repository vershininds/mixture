package com.vershininds.mixture.viewmodel;

import android.databinding.BaseObservable;

import java.util.UUID;

public abstract class AbstractViewModel extends BaseObservable implements MvpViewModel {

    protected String id;

    public AbstractViewModel() {
        this.id = UUID.randomUUID().toString();
    }

    protected AbstractViewModel(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void invalidate() {
        notifyChange();
    }
}