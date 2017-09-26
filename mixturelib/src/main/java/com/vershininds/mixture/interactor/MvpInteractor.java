package com.vershininds.mixture.interactor;

public interface MvpInteractor<L extends MvpInteractor.Listener> {
    enum State {
        WORKING,
        IDLE
    }

    interface Listener {}

    State getState();
    void setListener(L listener);
    void destroy();
    void reset();

    abstract class PendingResult<T, L> {
        private final T data;
        private final Throwable error;

        public PendingResult(T data, Throwable error) {
            this.data = data;
            this.error = error;
        }

        public abstract void deliver(L target);

        public T getData() {
            return data;
        }

        public Throwable getError() {
            return error;
        }

    }
}
