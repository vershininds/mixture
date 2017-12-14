package com.example.viper.presentation.modules.sample2.view;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viper.BR;
import com.example.viper.R;
import com.example.viper.application.AppDelegate;
import com.example.viper.data.SampleObject;
import com.example.viper.databinding.FragmentSample2Binding;
import com.example.viper.presentation.common.dbadapter.ListConfig;
import com.example.viper.presentation.common.dbadapter.adapter.BindableAdapter;
import com.example.viper.presentation.common.dbadapter.listener.ActionClickListener;
import com.example.viper.presentation.modules.sample2.assembly.Sample2Component;
import com.example.viper.presentation.modules.sample2.assembly.Sample2DaggerModule;
import com.example.viper.presentation.modules.sample2.contract.Sample2VmContract;
import com.example.viper.presentation.modules.sample2.view.adapters.ActionType;
import com.example.viper.presentation.modules.sample2.view.adapters.DataDelegate;
import com.vershininds.mixture.view.AbstractFragment;

import java.util.List;


public class Sample2Fragment extends AbstractFragment<Sample2VmContract.ViewModel, Sample2VmContract.Presenter> implements ActionClickListener {

    public static final String TAG = Sample2Fragment.class.getSimpleName();

    private Sample2Component diComponent;
    private FragmentSample2Binding mBinding;

    private BindableAdapter<List<SampleObject>> mAdapter;

    public static Fragment newInstance() {
        return new Sample2Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sample2, container, false);

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
                .sample2Component(new Sample2DaggerModule());
    }

    @Override
    protected Sample2VmContract.Presenter createPresenter() {
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
            Sample2VmContract.ViewModel vm = (Sample2VmContract.ViewModel) observable;
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
