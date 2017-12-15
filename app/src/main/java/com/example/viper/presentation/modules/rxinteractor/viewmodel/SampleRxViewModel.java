package com.example.viper.presentation.modules.rxinteractor.viewmodel;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

import javax.inject.Inject;

import com.example.viper.BR;
import com.example.viper.data.SampleObject;
import com.example.viper.presentation.modules.rxinteractor.contract.SampleRxVmContract;
import com.vershininds.mixture.viewmodel.AbstractViewModel;


@Parcel
public class SampleRxViewModel extends AbstractViewModel
        implements SampleRxVmContract.ViewModel {

    protected SampleRxVmContract.State state;
    protected String error;
    protected List<SampleObject> sampleObjectList;


    @Inject
    public SampleRxViewModel() {
        super();
        this.state = SampleRxVmContract.State.INITIAL;

        notifyChange();
    }

    @ParcelConstructor
    public SampleRxViewModel(String id, SampleRxVmContract.State state, String error, List<SampleObject> sampleObjectList) {
        super(id);
        this.state = state;
        this.error = error;
        this.sampleObjectList = sampleObjectList;

        notifyChange();
    }

    @Override
    public SampleRxVmContract.State getState() {
        return state;
    }

    @Override
    public void setState(SampleRxVmContract.State state) {
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
