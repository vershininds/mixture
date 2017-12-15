package com.vershininds.mixture.sample.presentation.modules.rxinteractor.view;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.assembly.SampleRxComponent;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.assembly.SampleRxDaggerModule;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.view.adapters.ActionType;
import com.vershininds.mixture.sample.BR;
import com.vershininds.mixture.sample.R;
import com.vershininds.mixture.sample.application.AppDelegate;
import com.vershininds.mixture.sample.databinding.FragmentSampleRxBinding;
import com.vershininds.mixture.sample.presentation.common.dbadapter.ListConfig;
import com.vershininds.mixture.sample.presentation.common.dbadapter.adapter.BindableAdapter;
import com.vershininds.mixture.sample.presentation.common.dbadapter.listener.ActionClickListener;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.contract.SampleRxVmContract;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.view.adapters.DataDelegate;
import com.vershininds.mixture.view.AbstractFragment;

import java.util.List;


public class SampleRxFragment extends AbstractFragment<SampleRxVmContract.ViewModel, SampleRxVmContract.Presenter> implements ActionClickListener {

    public static final String TAG = SampleRxFragment.class.getSimpleName();

    private SampleRxComponent diComponent;
    private FragmentSampleRxBinding mBinding;

    private BindableAdapter<List<SampleObject>> mAdapter;

    public static Fragment newInstance() {
        return new SampleRxFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sample_rx, container, false);

        mAdapter = new BindableAdapter<>(new DataDelegate(this));

        mBinding.setViewModel(getViewModel());

        ListConfig listConfig = new ListConfig.Builder(mAdapter)
                .setItemAnimator(new DefaultItemAnimator())
                .setHasFixedSize(true)
                .setDefaultDividerEnabled(true)
                .build(getActivity());

        mAdapter.setItems(getViewModel().getData());
        listConfig.applyConfig(getActivity(), mBinding.rvData);

        return mBinding.getRoot();
    }

    @Override
    protected void injectDi() {
        diComponent = AppDelegate.get()
                .presentationComponents()
                .sample1Component(new SampleRxDaggerModule());
    }

    @Override
    protected SampleRxVmContract.Presenter createPresenter() {
        return diComponent.getPresenter();
    }

    @Override
    protected void beforeViewAttached() {
        super.beforeViewAttached();
        getViewModel().addOnPropertyChangedCallback(vmObserver);
    }

    @Override
    protected void afterViewDetached() {
        super.afterViewDetached();
        getViewModel().removeOnPropertyChangedCallback(vmObserver);
    }


    private Observable.OnPropertyChangedCallback vmObserver = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable observable, int i) {
            SampleRxVmContract.ViewModel vm = (SampleRxVmContract.ViewModel) observable;
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
                getPresenter().itemSelected((SampleObject)model);
                break;
            default:
                break;
        }
    }
}
