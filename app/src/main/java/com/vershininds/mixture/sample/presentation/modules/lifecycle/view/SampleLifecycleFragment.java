package com.vershininds.mixture.sample.presentation.modules.lifecycle.view;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vershininds.mixture.sample.BR;
import com.vershininds.mixture.sample.R;
import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.databinding.FragmentSampleLifecycleBinding;
import com.vershininds.mixture.sample.presentation.common.dbadapter.ListConfig;
import com.vershininds.mixture.sample.presentation.common.dbadapter.adapter.BindableAdapter;
import com.vershininds.mixture.sample.presentation.common.dbadapter.listener.ActionClickListener;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.contract.SampleLifecycleVmContract;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.view.adapters.ActionType;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.view.adapters.DataDelegate;
import com.vershininds.mixture.view.AndroidComponent;

import java.util.List;


public class SampleLifecycleFragment extends Fragment implements AndroidComponent, ActionClickListener {

    public static final String TAG = SampleLifecycleFragment.class.getSimpleName();

    private SampleLifecycleDelegate delegate;

    private FragmentSampleLifecycleBinding mBinding;

    private BindableAdapter<List<SampleObject>> mAdapter;

    public static Fragment newInstance() {
        return new SampleLifecycleFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate = new SampleLifecycleDelegate(getLifecycle(), this, vmObserver);
        delegate.restoreInstanceState(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sample_lifecycle, container, false);

        mAdapter = new BindableAdapter<>(new DataDelegate(this));

        SampleLifecycleVmContract.ViewModel vm = delegate.getViewModel();
        mBinding.setViewModel(vm);

        ListConfig listConfig = new ListConfig.Builder(mAdapter)
                .setItemAnimator(new DefaultItemAnimator())
                .setHasFixedSize(true)
                .setDefaultDividerEnabled(true)
                .build(getActivity());

        mAdapter.setItems(vm.getData());
        listConfig.applyConfig(getActivity(), mBinding.rvData);

        return mBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState = delegate.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    private Observable.OnPropertyChangedCallback vmObserver = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable observable, int i) {
            SampleLifecycleVmContract.ViewModel vm = (SampleLifecycleVmContract.ViewModel) observable;
            if (i == BR.data) {
                mAdapter.setItems(vm.getData());
                mAdapter.notifyDataSetChanged();
            } else if (i == BR.error) {
                mBinding.txtError.setText(vm.getError());
            }
        }
    };

    @Override
    public void onActionClick(View view, String actionType, Object model) {
        switch (actionType) {
            case ActionType.CLICK:
                delegate.getPresenter().itemSelected((SampleObject)model);
                break;
            default:
                break;
        }
    }

    @Override
    public FragmentManager getSupportFragmentManager() {
        return getFragmentManager();
    }
}
