package com.vershininds.mixture.router;


public interface MvpRouter<L extends MvpRouter.Listener> {
    interface Listener {}

    void setListener(L listener);
}
