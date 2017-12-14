package com.example.viper.presentation.common.dbadapter.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.viper.presentation.common.dbadapter.delegate.IdHolder;
import com.hannesdorfmann.adapterdelegates2.AbsDelegationAdapter;
import com.hannesdorfmann.adapterdelegates2.AdapterDelegate;
import com.hannesdorfmann.adapterdelegates2.AdapterDelegatesManager;


/**
 * RecyclerView Adapter for using with data binding. Dataset can be any type, not only List of items.
 * Based on AdapterDelegates Library by Hannes Dorfmann https://github.com/sockeqwe/AdapterDelegates
 *
 * @param <T> The type of the datasoure / items
 */
public abstract class BaseBindableAdapter<T> extends AbsDelegationAdapter<T> {

    /**
     * Creates Adapter with empty adapter delegates manager
     */
    public BaseBindableAdapter() {
    }

    public BaseBindableAdapter(@NonNull AdapterDelegatesManager<T> delegatesManager) {
        super(delegatesManager);
    }

    public BaseBindableAdapter(AdapterDelegate<T>... delegates) {
        super(new AdapterDelegatesManager<T>());
        for (AdapterDelegate<T> delegate : delegates) delegatesManager.addDelegate(delegate);
    }

    public BaseBindableAdapter(T items, AdapterDelegatesManager<T> delegatesManager) {
        super(delegatesManager);
        setItems(items);
    }

    public BaseBindableAdapter(T items, AdapterDelegate<T>... delegates) {
        this(delegates);
        setItems(items);
    }

    /**
     * Get item id if specific AdapterDelegate implement IdHolder interface
     *
     * @param position position of item in data source
     * @return the item id
     */
    @Override
    public long getItemId(int position) {
        final int viewType = delegatesManager.getItemViewType(items, position);
        final AdapterDelegate<T> delegate = delegatesManager.getDelegateForViewType(viewType);
        //noinspection unchecked
        return delegate instanceof IdHolder ? ((IdHolder) delegate).getItemId(items, position) : RecyclerView.NO_ID;
    }

}