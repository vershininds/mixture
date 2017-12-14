package com.example.viper.presentation.common.dbadapter.delegate;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.viper.presentation.common.dbadapter.adapter.BindingHolder;
import com.hannesdorfmann.adapterdelegates2.AdapterDelegate;


/**
 * Base AdapterDelegate to use with data binding
 * Based on AdapterDelegates Library by Hannes Dorfmann https://github.com/sockeqwe/AdapterDelegates
 *
 * @param <T>  The type of the data source
 * @param <VB> The type of View Data Binding
 */
public abstract class BaseBindingAdapterDelegate<T, VB extends ViewDataBinding> implements AdapterDelegate<T> {


    @NonNull
    @Override
    public abstract BindingHolder<VB> onCreateViewHolder(ViewGroup parent);

    @Override
    public void onBindViewHolder(@NonNull T items, int position, @NonNull RecyclerView.ViewHolder holder) {
        //noinspection unchecked
        final BindingHolder<VB> bindingHolder = (BindingHolder<VB>) holder;
        onBindViewHolder(items, position, bindingHolder);
        bindingHolder.getBinding().executePendingBindings();
    }

    public abstract void onBindViewHolder(@NonNull T items, int position, @NonNull BindingHolder<VB> holder);
}