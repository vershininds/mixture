package com.vershininds.mixture.router;

public abstract class AbstractRouter<L extends MvpRouter.Listener> implements MvpRouter<L> {
    private L listener;

    @Override
    public void setListener(L listener) {
        this.listener = listener;
    }

    protected L getListener() {
        return listener;
    }
}
