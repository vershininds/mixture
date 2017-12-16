package com.vershininds.mixture.helper;

import android.support.annotation.Nullable;

import com.vershininds.mixture.presenter.MvpPresenter;

import java.util.HashMap;
import java.util.Map;


/**
 * Class for storage of presenter for the time of re-creation view(activity or fragment)
 */
public class PresenterStorage {
    private static PresenterStorage sInstance;

    private final Map<String, ? super MvpPresenter> cache;

    public static synchronized PresenterStorage getInstance() {
        if (sInstance == null) {
            sInstance = new PresenterStorage();
        }
        return sInstance;
    }

    /**
     * save presenter in simple map
     * @param uid unique id, key for map
     * @param presenter {@link MvpPresenter}
     */
    public void save(String uid, MvpPresenter presenter) {
        cache.put(uid, presenter);
    }

    /**
     * @param uid unique id, key for map
     * @param <T> extends {@link MvpPresenter}
     * @return the {@link MvpPresenter} of the removed mapping or null if no mapping for the specified key was found
     */
    @Nullable
    public <T extends MvpPresenter> T evict(String uid) {
        return (T) cache.remove(uid);
    }

    private PresenterStorage() {
        cache = new HashMap<>();
    }
}

