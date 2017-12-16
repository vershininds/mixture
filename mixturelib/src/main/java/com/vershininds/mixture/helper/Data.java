package com.vershininds.mixture.helper;


import android.support.annotation.CheckResult;

/**
 *  Helper class for work with data state
 * @param <T> data type
 */
public class Data<T> {

    public enum State {
        LOADING, REFRESHING, ERROR, DATA, EMPTY
    }

    private final boolean loading;
    public final Throwable error;
    public final T content;

    private Data(boolean loading, Throwable error, T content) {
        this.loading = loading;
        this.error = error;
        this.content = content;
    }

    /**
     * @param <T> data type
     * @return data object with loading state
     */
    public static <T> Data<T> loading() {
        return new Data<>(true, null, null);
    }

    /**
     * @param error usual Throwable
     * @param <T> data type
     * @return data object with error state
     */
    public static <T> Data<T> error(Throwable error) {
        return new Data<>(false, error, null);
    }

    /**
     * @param data content
     * @param <T> data type
     * @return data object with content state
     */
    public static <T> Data<T> data(T data) {
        return new Data<>(false, null, data);
    }

    /**
     * @param <T> data type
     * @return data object with empty state
     */
    public static <T> Data<T> empty() {
        return new Data<>(false, null, null);
    }


    /**
     * @return data object with refreshing state
     */
    @CheckResult
    public Data<T> refreshing() {
        return new Data<>(true, error, content);
    }


    /**
     * @return current data state {@link State}
     */
    public State getState() {
        return loading ?
                (content == null && error == null ? State.LOADING : State.REFRESHING) :
                (content == null ?
                        (error == null ? State.EMPTY : State.ERROR) :
                        State.DATA);
    }
}