package com.example.viper.presentation.modules.sample1.view;

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
import com.example.viper.databinding.FragmentSample1Binding;
import com.example.viper.presentation.common.dbadapter.ListConfig;
import com.example.viper.presentation.common.dbadapter.adapter.BindableAdapter;
import com.example.viper.presentation.common.dbadapter.listener.ActionClickListener;
import com.example.viper.presentation.modules.sample1.assembly.Sample1Component;
import com.example.viper.presentation.modules.sample1.assembly.Sample1DaggerModule;
import com.example.viper.presentation.modules.sample1.contract.Sample1VmContract;
import com.example.viper.presentation.modules.sample1.view.adapters.ActionType;
import com.example.viper.presentation.modules.sample1.view.adapters.DataDelegate;
import com.vershininds.mixture.view.AbstractFragment;

import java.util.List;


public class Sample1Fragment extends AbstractFragment<Sample1VmContract.ViewModel, Sample1VmContract.Presenter> implements ActionClickListener {

    public static final String TAG = Sample1Fragment.class.getSimpleName();

    private Sample1Component diComponent;
    private FragmentSample1Binding mBinding;

    private BindableAdapter<List<SampleObject>> mAdapter;

    public static Fragment newInstance() {
        return new Sample1Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sample1, container, false);

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
                .sample1Component(new Sample1DaggerModule());
    }

    @Override
    protected Sample1VmContract.Presenter createPresenter() {
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
            Sample1VmContract.ViewModel vm = (Sample1VmContract.ViewModel) observable;
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
