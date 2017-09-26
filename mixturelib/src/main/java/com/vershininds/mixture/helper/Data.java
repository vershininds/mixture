package com.vershininds.mixture.helper;


import android.support.annotation.CheckResult;

public class Data<T> {
    public enum State {
        LOADING, REFRESHING, ERROR, DATA, EMPTY
    }

    private final boolean loading;
    public final Throwable error;
    public final T content;

    public static <T> Data<T> loading() {
        return new Data<>(true, null, null);
    }

    public static <T> Data<T> error(Throwable error) {
        return new Data<>(false, error, null);
    }

    public static <T> Data<T> data(T data) {
        return new Data<>(false, null, data);
    }

    public static <T> Data<T> empty() {
        return new Data<>(false, null, null);
    }

    private Data(boolean loading, Throwable error, T content) {
        this.loading = loading;
        this.error = error;
        this.content = content;
    }

    public State getState() {
        return loading ?
                (content == null && error == null ? State.LOADING : State.REFRESHING) :
                (content == null ?
                        (error == null ? State.EMPTY : State.ERROR) :
                        State.DATA);
    }

    @CheckResult
    public Data<T> refreshing() {
        return new Data<>(true, error, content);
    }
}