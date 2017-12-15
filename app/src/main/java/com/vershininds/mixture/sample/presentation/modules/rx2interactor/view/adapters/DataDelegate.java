package com.vershininds.mixture.sample.presentation.modules.rx2interactor.view.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vershininds.mixture.sample.R;
import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.databinding.ItemDataBinding;
import com.vershininds.mixture.sample.presentation.common.dbadapter.adapter.BindingHolder;
import com.vershininds.mixture.sample.presentation.common.dbadapter.delegate.ActionAdapterDelegate;
import com.vershininds.mixture.sample.presentation.common.dbadapter.listener.ActionClickListener;

import java.util.List;


public class DataDelegate extends ActionAdapterDelegate<SampleObject, ItemDataBinding> {

    public DataDelegate(final ActionClickListener actionHandler) {
        super(actionHandler);
    }

    @Override
    public boolean isForViewType(@NonNull final List<SampleObject> items, final int position) {
        return true;
    }

    @NonNull
    @Override
    public BindingHolder<ItemDataBinding> onCreateViewHolder(final ViewGroup parent) {
        return BindingHolder.newInstance(R.layout.item_data, LayoutInflater.from(parent.getContext()), parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull final List<SampleObject> items, final int position, @NonNull final BindingHolder<ItemDataBinding> holder) {
        final SampleObject item = items.get(position);
        holder.getBinding().setItemData(item);
        holder.getBinding().setActionHandler(getActionHandler());
    }

    @Override
    public long getItemId(final List<SampleObject> items, final int position) {
        return items.get(position).getName().hashCode();
    }
}