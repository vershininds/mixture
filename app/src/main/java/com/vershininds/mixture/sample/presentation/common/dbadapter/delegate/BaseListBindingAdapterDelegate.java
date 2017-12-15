package com.vershininds.mixture.sample.presentation.common.dbadapter.delegate;

import android.databinding.ViewDataBinding;

import java.util.List;

import static android.support.v7.widget.RecyclerView.NO_ID;

/**
 * Base AdapterDelegate for items with ids
 * Based on AdapterDelegates Library by Hannes Dorfmann https://github.com/sockeqwe/AdapterDelegates
 *
 * @param <T> The type of the data source
 * @param <VB> The type of View Data Binding
 */
public abstract class BaseListBindingAdapterDelegate<T, VB extends ViewDataBinding> extends BaseBindingAdapterDelegate<List<T>, VB> implements IdHolder<List<T>> {

    @Override
    public long getItemId(final List<T> items, final int position) {
        return NO_ID;
    }
}