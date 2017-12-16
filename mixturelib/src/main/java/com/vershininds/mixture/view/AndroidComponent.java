package com.vershininds.mixture.view;

import android.app.Activity;
import android.support.v4.app.FragmentManager;

/**
 * This interface need implement view.
 * Used by the presenter for actions who need view context
 */
public interface AndroidComponent {
    Activity getActivity();
    FragmentManager getSupportFragmentManager();
}
