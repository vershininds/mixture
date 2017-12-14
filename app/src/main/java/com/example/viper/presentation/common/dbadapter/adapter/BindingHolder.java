package com.example.viper.presentation.common.dbadapter.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Recycler View Holder to use with data mBinding
 *
 * @param <VB> The type of view data binding
 */
public class BindingHolder<VB extends ViewDataBinding> extends RecyclerView.ViewHolder {

    /**
     * View data binding of this holder
     */
    private VB mBinding;

    /**
     * Creates new View Holder from provided layout
     *
     * @param layoutId       The layout resource ID of the layout to inflate.
     * @param inflater       The LayoutInflater used to inflate the binding layout.
     * @param parent         Optional view to be the parent of the generated hierarchy
     * @param attachToParent Whether the inflated hierarchy should be attached to the
     *                       parent parameter. If false, parent is only used to create
     *                       the correct subclass of LayoutParams for the root view in the XML.
     * @param <VB>           The type of view data binding
     * @return The newly-created view-holder for the binding with inflated layout.
     */
    public static <VB extends ViewDataBinding> BindingHolder<VB> newInstance(
            @LayoutRes int layoutId, LayoutInflater inflater,
            @Nullable ViewGroup parent, boolean attachToParent) {

        final VB vb = DataBindingUtil.inflate(inflater, layoutId, parent, attachToParent);
        return new BindingHolder<>(vb);
    }

    /**
     * Creates new View Holder from provided binding
     *
     * @param binding The view data binding class for this view-holder
     */
    public BindingHolder(VB binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    /**
     * Returns view data binding of this holder
     *
     * @return view data binding of this holder
     */
    public VB getBinding() {
        return mBinding;
    }

}