package com.vershininds.mixture.sample.presentation.modules.lifecycle.viewmodel;

import com.vershininds.mixture.sample.BR;
import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.presentation.modules.lifecycle.contract.SampleLifecycleVmContract;
import com.vershininds.mixture.viewmodel.AbstractViewModel;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

import javax.inject.Inject;


@Parcel
public class SampleLifecycleViewModel extends AbstractViewModel
        implements SampleLifecycleVmContract.ViewModel {

    protected SampleLifecycleVmContract.State state;
    protected String error;
    protected List<SampleObject> sampleObjectList;


    @Inject
    public SampleLifecycleViewModel() {
        super();
        this.state = SampleLifecycleVmContract.State.INITIAL;

        notifyChange();
    }

    @ParcelConstructor
    public SampleLifecycleViewModel(String id, SampleLifecycleVmContract.State state, String error, List<SampleObject> sampleObjectList) {
        super(id);
        this.state = state;
        this.error = error;
        this.sampleObjectList = sampleObjectList;

        notifyChange();
    }

    @Override
    public SampleLifecycleVmContract.State getState() {
        return state;
    }

    @Override
    public void setState(SampleLifecycleVmContract.State state) {
        this.state = state;
        notifyPropertyChanged(BR.state);
    }

    @Override
    public String getError() {
        return error;
    }

    @Override
    public void setError(String error) {
        this.error = error;
        notifyPropertyChanged(BR.error);
    }

    @Override
    public List<SampleObject> getData() {
        return sampleObjectList;
    }

    @Override
    public void setData(List<SampleObject> data) {
        this.sampleObjectList = data;
        notifyPropertyChanged(BR.data);
    }
}
