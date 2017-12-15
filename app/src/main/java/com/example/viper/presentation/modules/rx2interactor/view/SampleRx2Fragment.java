package com.example.viper.presentation.modules.rx2interactor.view;

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
import com.example.viper.databinding.FragmentSampleRx2Binding;
import com.example.viper.presentation.common.dbadapter.ListConfig;
import com.example.viper.presentation.common.dbadapter.adapter.BindableAdapter;
import com.example.viper.presentation.common.dbadapter.listener.ActionClickListener;
import com.example.viper.presentation.modules.rx2interactor.assembly.SampleRx2Component;
import com.example.viper.presentation.modules.rx2interactor.assembly.SampleRx2DaggerModule;
import com.example.viper.presentation.modules.rx2interactor.contract.SampleRx2VmContract;
import com.example.viper.presentation.modules.rx2interactor.view.adapters.ActionType;
import com.example.viper.presentation.modules.rx2interactor.view.adapters.DataDelegate;
import com.vershininds.mixture.view.AbstractFragment;

import java.util.List;


public class SampleRx2Fragment extends AbstractFragment<SampleRx2VmContract.ViewModel, SampleRx2VmContract.Presenter> implements ActionClickListener {

    public static final String TAG = SampleRx2Fragment.class.getSimpleName();

    private SampleRx2Component diComponent;
    private FragmentSampleRx2Binding mBinding;

    private BindableAdapter<List<SampleObject>> mAdapter;

    public static Fragment newInstance() {
        return new SampleRx2Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sample_rx2, container, false);

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
                .sample2Component(new SampleRx2DaggerModule());
    }

    @Override
    protected SampleRx2VmContract.Presenter createPresenter() {
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
            SampleRx2VmContract.ViewModel vm = (SampleRx2VmContract.ViewModel) observable;
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
