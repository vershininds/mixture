package com.vershininds.mixture.interactor;

/**
 * Base interactor interface.
 * Interactor contains all the business logic required for module.
 * @param <L> {@link Listener} target class who get operation result from interactor
 */
public interface MvpInteractor<L extends MvpInteractor.Listener> {
    enum State {
        WORKING,
        IDLE
    }

    interface Listener {}

    /**
     * @return {@link State} interactor, is working or idle
     */
    State getState();

    /**
     * @param listener {@link Listener} target class who get operation result from interactor
     */
    void setListener(L listener);

    /**
     * abort current job (load)
     */
    void reset();
    void destroy();

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
