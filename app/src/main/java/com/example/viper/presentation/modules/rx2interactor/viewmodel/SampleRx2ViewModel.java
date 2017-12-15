package com.example.viper.presentation.modules.rx2interactor.viewmodel;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

import javax.inject.Inject;

import com.example.viper.BR;
import com.example.viper.data.SampleObject;
import com.example.viper.presentation.modules.rx2interactor.contract.SampleRx2VmContract;
import com.vershininds.mixture.viewmodel.AbstractViewModel;


@Parcel
public class SampleRx2ViewModel extends AbstractViewModel
        implements SampleRx2VmContract.ViewModel {

    protected SampleRx2VmContract.State state;
    protected String error;
    protected List<SampleObject> sampleObjectList;


    @Inject
    public SampleRx2ViewModel() {
        super();
        this.state = SampleRx2VmContract.State.INITIAL;

        notifyChange();
    }

    @ParcelConstructor
    public SampleRx2ViewModel(String id, SampleRx2VmContract.State state, String error, List<SampleObject> sampleObjectList) {
        super(id);
        this.state = state;
        this.error = error;
        this.sampleObjectList = sampleObjectList;

        notifyChange();
    }

    @Override
    public SampleRx2VmContract.State getState() {
        return state;
    }

    @Override
    public void setState(SampleRx2VmContract.State state) {
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
