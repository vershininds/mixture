package com.example.viper.presentation.common.dbadapter.delegate;

import android.databinding.ViewDataBinding;

import com.example.viper.presentation.common.dbadapter.listener.ActionClickListener;


/**
 * Base AdapterDelegate to use with data binding and with ability to set actionHandler
 * Based on AdapterDelegates Library by Hannes Dorfmann https://github.com/sockeqwe/AdapterDelegates
 * and on Action-Handler Library by Roman Donchenko https://github.com/drstranges/ActionHandler
 *
 * @param <T>  The type of the data source
 * @param <VB> The type of View Data Binding
 */
public abstract class ActionAdapterDelegate<T, VB extends ViewDataBinding> extends BaseListBindingAdapterDelegate<T, VB> {

    protected ActionClickListener mActionHandler;

    public ActionAdapterDelegate(final ActionClickListener actionHandler) {
        mActionHandler = actionHandler;
    }

    public ActionClickListener getActionHandler() {
        return mActionHandler;
    }
}