/*
 *  Copyright Roman Donchenko. All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.vershininds.mixture.sample.presentation.common.dbadapter.action;

import android.content.Context;
import android.view.View;

import com.vershininds.mixture.sample.presentation.common.dbadapter.listener.ActionFireInterceptor;
import com.vershininds.mixture.sample.presentation.common.dbadapter.listener.OnActionDismissListener;
import com.vershininds.mixture.sample.presentation.common.dbadapter.listener.OnActionErrorListener;
import com.vershininds.mixture.sample.presentation.common.dbadapter.listener.OnActionFiredListener;

import java.util.HashSet;
import java.util.Set;


/**
 * Extent from BaseAction all you custom actions.
 * BaseAction contain base logic to notify listeners if action fired.
 *
 * @param <M> base type, which can be handled
 */
public abstract class BaseAction<M> implements Action<M> {

    /**
     * Listeners for action fired events.
     */
    protected Set<OnActionFiredListener> mActionFiredListeners = new HashSet<>(1);

    /**
     * Listeners for action error events.
     */
    protected Set<OnActionErrorListener> mActionErrorListeners = new HashSet<>(1);

    /**
     * Listeners for action dismiss events.
     */
    protected Set<OnActionDismissListener> mActionDismissListeners = new HashSet<>(1);

    /**
     * Callbacks to be invoked after a view with an action is clicked and before action handling started.
     * Can intercept an action to prevent it to be fired
     */
    protected Set<ActionFireInterceptor> mActionFireInterceptors = new HashSet<>(1);

    /**
     * Add a listener that will be called when method {@link #notifyOnActionFired(View, String, Object)}
     * called. Generally if action fired successfully.
     *
     * @param listener The listener that will be called when action fired successfully.
     */
    public void addActionFiredListener(OnActionFiredListener listener) {
        if (listener != null) mActionFiredListeners.add(listener);
    }

    /**
     * Remove a listener for action fired events.
     *
     * @param listener The listener for action fired events.
     */
    public void removeActionFireListener(OnActionFiredListener listener) {
        if (listener != null) mActionFiredListeners.remove(listener);
    }

    /**
     * Remove all listeners for action fired events.
     */
    public void removeAllActionFireListeners() {
        mActionFiredListeners.clear();
    }


    /**
     * Add a listener that will be called when method {@link #notifyOnActionError(Throwable, View, String, Object)}
     * called. Generally if action fired with error.
     *
     * @param listener The listener that will be called when action fired with error.
     */
    public void addActionErrorListener(OnActionErrorListener listener) {
        if (listener != null) mActionErrorListeners.add(listener);
    }

    /**
     * Remove a listener for action error events.
     *
     * @param listener The listener for action error events.
     */
    public void removeActionErrorListener(OnActionErrorListener listener) {
        if (listener != null) mActionErrorListeners.remove(listener);
    }

    /**
     * Remove all listeners for action error events.
     */
    public void removeAllActionErrorListeners() {
        mActionErrorListeners.clear();
    }

    /**
     * Add a listener that will be called when method {@link #notifyOnActionDismiss(String, View, String, Object)}
     * called. Generally if action was dismissed by user.
     *
     * @param listener The listener that will be called when action dismissed.
     */
    public void addActionDismissListener(OnActionDismissListener listener) {
        if (listener != null) mActionDismissListeners.add(listener);
    }

    /**
     * Remove a listener for action dismiss events.
     *
     * @param listener The listener for action dismiss events.
     */
    public void removeActionDismissListener(OnActionDismissListener listener) {
        if (listener != null) mActionDismissListeners.remove(listener);
    }

    /**
     * Remove all listeners for action dismiss events.
     */
    public void removeAllActionDismissListeners() {
        mActionDismissListeners.clear();
    }

    /**
     * Add callback to be invoked right before specific action will be fired.
     * Can intercept an action to prevent it to be fired
     *
     * @param interceptor The interceptor.
     */
    public void addActionFireInterceptor(ActionFireInterceptor interceptor) {
        if (interceptor != null) mActionFireInterceptors.add(interceptor);
    }

    /**
     * Remove interceptor.
     *
     * @param interceptor The interceptor.
     */
    public void removeActionFireInterceptor(ActionFireInterceptor interceptor) {
        if (interceptor != null) mActionFireInterceptors.remove(interceptor);
    }

    /**
     * Remove all interceptors.
     */
    public void removeAllActionFireInterceptors() {
        mActionFireInterceptors.clear();
    }

    /**
     * Remove all listeners for action fire, error and dismiss events.
     */
    public void removeAllActionListeners() {
        mActionFiredListeners.clear();
        mActionErrorListeners.clear();
        mActionDismissListeners.clear();
        mActionFireInterceptors.clear();
    }


    /**
     * Notify any registered listeners that the action has been fired.
     *
     * @param view       The View, which can be used for prepare any visual effect (like animation),
     *                   Generally it is that view which was clicked and initiated action to fire.
     * @param actionType type of the action
     * @param model      model, which was handled
     */
    public void notifyOnActionFired(View view, String actionType, Object model) {
        notifyOnActionFired(view, actionType, model, null);
    }

    /**
     * Notify any registered listeners that the action has been fired.
     *
     * @param view       The View, which can be used for prepare any visual effect (like animation),
     *                   Generally it is that view which was clicked and initiated action to fire.
     * @param actionType type of the action
     * @param model      model, which was handled
     * @param result     The result of action
     */
    public void notifyOnActionFired(View view, String actionType, Object model, Object result) {
        for (OnActionFiredListener listener : mActionFiredListeners) {
            listener.onActionFired(view, actionType, model, result);
        }
    }

    /**
     * Notify any registered listeners that the action has been executed with error.
     *
     * @param throwable  The error
     * @param view       The View, which can be used for prepare any visual effect (like animation),
     *                   Generally it is that view which was clicked and initiated action to fire.
     * @param actionType type of the action
     * @param model      model, which was handled
     */
    public void notifyOnActionError(Throwable throwable, View view, String actionType, Object model) {
        for (OnActionErrorListener listener : mActionErrorListeners) {
            listener.onActionError(throwable, view, actionType, model);
        }
    }

    /**
     * Notify any registered listeners that the action has been executed with error.
     *
     * @param reason     The reason to dismiss
     * @param view       The View, which can be used for prepare any visual effect (like animation),
     *                   Generally it is that view which was clicked and initiated action to fire.
     * @param actionType type of the action
     * @param model      model, which was handled
     */
    public void notifyOnActionDismiss(String reason, View view, String actionType, Object model) {
        for (OnActionDismissListener listener : mActionDismissListeners) {
            listener.onActionDismiss(reason, view, actionType, model);
        }
    }

    protected boolean interceptActionFire(Context context, View view, String actionType, Object model, Action action) {
        if (mActionFireInterceptors != null) {
            for (ActionFireInterceptor interceptor : mActionFireInterceptors) {
                if (interceptor.onInterceptActionFire(context, view, actionType, model, action)) return true;
            }
        }
        return false;
    }
}