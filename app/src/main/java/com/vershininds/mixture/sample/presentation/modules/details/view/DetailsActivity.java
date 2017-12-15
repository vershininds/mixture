package com.vershininds.mixture.sample.presentation.modules.details.view;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.vershininds.mixture.sample.R;
import com.vershininds.mixture.sample.application.AppDelegate;
import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.databinding.ActivityDetailsBinding;
import com.vershininds.mixture.sample.presentation.modules.details.assembly.DetailsComponent;
import com.vershininds.mixture.sample.presentation.modules.details.assembly.DetailsDaggerModule;
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsVmContract;
import com.vershininds.mixture.view.AbstractActivity;

import org.parceler.Parcels;


public class DetailsActivity extends AbstractActivity<DetailsVmContract.ViewModel, DetailsVmContract.Presenter> {

    public static final String TAG = DetailsActivity.class.getSimpleName();
    public static final String KEY_DATA = TAG + "_KEY_DATA";

    private DetailsComponent diComponent;
    private ActivityDetailsBinding mBinding;


    public static Intent createIntent(Activity source, @NonNull SampleObject data) {
        Intent intent = new Intent(source, DetailsActivity.class);
        intent.putExtra(KEY_DATA, Parcels.wrap(data));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        mBinding.setViewModel(getViewModel());
        mBinding.btnFinish.setOnClickListener(v -> getPresenter().onClickFinish());
    }

    @Override
    protected void injectDi() {
        SampleObject data = Parcels.unwrap(getIntent().getParcelableExtra(KEY_DATA));

        diComponent = AppDelegate.get()
                .presentationComponents()
                .detailsComponent(new DetailsDaggerModule(data));
    }

    @Override
    protected DetailsVmContract.Presenter createPresenter() {
        return diComponent.getPresenter();
    }
}
