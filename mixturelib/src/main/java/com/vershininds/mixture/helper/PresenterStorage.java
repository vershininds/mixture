package com.vershininds.mixture.helper;

import java.util.HashMap;
import java.util.Map;

import com.vershininds.mixture.presenter.MvpPresenter;


public class PresenterStorage {
    private static PresenterStorage sInstance;

    private final Map<String, ? super MvpPresenter> cache;

    public static synchronized PresenterStorage getInstance() {
        if (sInstance == null) {
            sInstance = new PresenterStorage();
        }
        return sInstance;
    }

    public void save(String uid, MvpPresenter presenter) {
        cache.put(uid, presenter);
    }

    public <T extends MvpPresenter> T evict(String uid) {
        return (T) cache.remove(uid);
    }

    private PresenterStorage() {
        cache = new HashMap<>();
    }
}

