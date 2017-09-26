package com.vershininds.mixture.viewmodel;

import android.databinding.Observable;

public interface MvpViewModel extends Observable {
    String getId();
    void invalidate();
}
