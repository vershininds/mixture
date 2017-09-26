package com.example.viper.presentation.modules.sample2.viewmodel;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

import javax.inject.Inject;

import com.example.viper.BR;
import com.example.viper.data.SampleObject;
import com.example.viper.presentation.modules.sample2.contract.Sample2VmContract;
import com.vershininds.mixture.viewmodel.AbstractViewModel;


@Parcel
public class Sample2ViewModel extends AbstractViewModel
        implements Sample2VmContract.ViewModel {

    protected Sample2VmContract.State state;
    protected String error;
    protected List<SampleObject> sampleObjectList;


    @Inject
    public Sample2ViewModel() {
        super();
        this.state = Sample2VmContract.State.INITIAL;

        notifyChange();
    }

    @ParcelConstructor
    public Sample2ViewModel(String id, Sample2VmContract.State state, String error, List<SampleObject> sampleObjectList) {
        super(id);
        this.state = state;
        this.error = error;
        this.sampleObjectList = sampleObjectList;

        notifyChange();
    }

    @Override
    public Sample2VmContract.State getState() {
        return state;
    }

    @Override
    public void setState(Sample2VmContract.State state) {
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
