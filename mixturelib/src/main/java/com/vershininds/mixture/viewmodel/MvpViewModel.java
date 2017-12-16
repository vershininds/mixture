package com.vershininds.mixture.viewmodel;

import android.databinding.Observable;


/**
 * ViewModel intermediary between presenter and view.
 * Simplifies the work of updating data on the view and saves state when view was destroyed
 */
public interface MvpViewModel extends Observable {
    String getId();
    void invalidate();
}
