package com.example.viper.presentation.modules.main.viewmodel;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import javax.inject.Inject;

import com.example.viper.BR;
import com.example.viper.presentation.modules.main.contract.MainVmContract;
import com.vershininds.mixture.viewmodel.AbstractViewModel;


@Parcel
public class MainViewModel extends AbstractViewModel
        implements MainVmContract.ViewModel {
    protected MainVmContract.State state;


    @Inject
    public MainViewModel() {
        super();
        this.state = MainVmContract.State.INITIAL;

        notifyChange();
    }

    @ParcelConstructor
    public MainViewModel(String id, MainVmContract.State state) {
        super(id);
        this.state = state;

        notifyChange();
    }

    @Override
    public MainVmContract.State getState() {
        return state;
    }

    @Override
    public void setState(MainVmContract.State state) {
        this.state = state;
        notifyPropertyChanged(BR.state);
    }

}
