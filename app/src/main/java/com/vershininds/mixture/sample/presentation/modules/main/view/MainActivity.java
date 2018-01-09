package com.vershininds.mixture.sample.presentation.modules.main.view;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.vershininds.mixture.sample.R;
import com.vershininds.mixture.sample.application.AppDelegate;
import com.vershininds.mixture.sample.databinding.ActivityMainBinding;
import com.vershininds.mixture.sample.presentation.modules.main.assembly.MainComponent;
import com.vershininds.mixture.sample.presentation.modules.main.assembly.MainDaggerModule;
import com.vershininds.mixture.sample.presentation.modules.main.contract.MainVmContract;
import com.vershininds.mixture.view.AbstractActivity;


public class MainActivity extends AbstractActivity<MainVmContract.ViewModel, MainVmContract.Presenter> {

    public static final String TAG = MainActivity.class.getSimpleName();

    private MainComponent diComponent;
    private ActivityMainBinding mBinding;

    public static Intent createIntent(Activity source) {
        Intent intent = new Intent(source, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void injectDi() {
        diComponent = AppDelegate.get()
                .presentationComponents()
                .mainComponent(new MainDaggerModule());
    }

    @Override
    protected MainVmContract.Presenter createPresenter() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_sample1:
                getPresenter().showSample1();
                break;
            case R.id.menu_item_sample2:
                getPresenter().showSample2();
                break;
            case R.id.menu_item_sample_lifecycle:
                getPresenter().showSampleLifecycle();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private Observable.OnPropertyChangedCallback vmObserver = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable observable, int i) {
            MainVmContract.ViewModel vm = (MainVmContract.ViewModel) observable;
            switch (i) {
                default:
                    break;
            }
        }
    };
}
