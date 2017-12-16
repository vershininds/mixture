package com.vershininds.mixture.router;


/**
 * Router responsible for the navigation between the modules(in simple terms navigation between screens)
 * @param <L> {@link Listener} target class who get operation result from router
 */
public interface MvpRouter<L extends MvpRouter.Listener> {
    interface Listener {}

    void setListener(L listener);
}
