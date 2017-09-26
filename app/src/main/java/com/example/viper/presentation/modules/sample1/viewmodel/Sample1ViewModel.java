package com.example.viper.presentation.modules.sample1.viewmodel;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

import javax.inject.Inject;

import com.example.viper.BR;
import com.example.viper.data.SampleObject;
import com.example.viper.presentation.modules.sample1.contract.Sample1VmContract;
import com.vershininds.mixture.viewmodel.AbstractViewModel;


@Parcel
public class Sample1ViewModel extends AbstractViewModel
        implements Sample1VmContract.ViewModel {

    protected Sample1VmContract.State state;
    protected String error;
    protected List<SampleObject> sampleObjectList;


    @Inject
    public Sample1ViewModel() {
        super();
        this.state = Sample1VmContract.State.INITIAL;

        notifyChange();
    }

    @ParcelConstructor
    public Sample1ViewModel(String id, Sample1VmContract.State state, String error, List<SampleObject> sampleObjectList) {
        super(id);
        this.state = state;
        this.error = error;
        this.sampleObjectList = sampleObjectList;

        notifyChange();
    }

    @Override
    public Sample1VmContract.State getState() {
        return state;
    }

    @Override
    public void setState(Sample1VmContract.State state) {
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
