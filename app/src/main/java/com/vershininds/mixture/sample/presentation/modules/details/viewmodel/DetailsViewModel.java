package com.vershininds.mixture.sample.presentation.modules.details.viewmodel;

import com.vershininds.mixture.sample.BR;
import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsVmContract;
import com.vershininds.mixture.viewmodel.AbstractViewModel;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import javax.inject.Inject;


@Parcel
public class DetailsViewModel extends AbstractViewModel
        implements DetailsVmContract.ViewModel {

    protected DetailsVmContract.State state;
    protected String error;
    protected SampleObject data;


    @Inject
    public DetailsViewModel(SampleObject data) {
        super();
        this.state = DetailsVmContract.State.INITIAL;
        this.data = data;

        notifyChange();
    }

    @ParcelConstructor
    public DetailsViewModel(String id, DetailsVmContract.State state, String error, SampleObject data) {
        super(id);
        this.state = state;
        this.error = error;
        this.data = data;

        notifyChange();
    }

    @Override
    public DetailsVmContract.State getState() {
        return state;
    }

    @Override
    public void setState(DetailsVmContract.State state) {
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
    public SampleObject getData() {
        return data;
    }

    @Override
    public void setData(SampleObject data) {
        this.data = data;
        notifyPropertyChanged(BR.data);
    }

    @Override
    public String getSomeInformation() {
        String result = "No description";

        result = data == null ? result : (data.getName() + "\n" + data.getDescription());
        return result;
    }
}
