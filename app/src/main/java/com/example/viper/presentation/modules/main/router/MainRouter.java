package com.example.viper.presentation.modules.main.router;

import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import javax.inject.Inject;

import com.example.viper.R;
import com.example.viper.data.SampleObject;
import com.example.viper.presentation.modules.main.contract.MainRouterContract;
import com.example.viper.presentation.modules.sample1.Sample1ModuleInput;
import com.example.viper.presentation.modules.sample2.Sample2ModuleInput;
import com.vershininds.mixture.router.AbstractRouter;
import com.vershininds.mixture.view.AndroidComponent;


public class MainRouter extends AbstractRouter<MainRouterContract.Presenter>
        implements MainRouterContract.Router {

    private final Sample1ModuleInput sample1ModuleInput;
    private final Sample2ModuleInput sample2ModuleInput;

    @Inject
    public MainRouter(Sample1ModuleInput sample1ModuleInput, Sample2ModuleInput sample2ModuleInput) {
        this.sample1ModuleInput = sample1ModuleInput;
        this.sample2ModuleInput = sample2ModuleInput;
    }

    @Override
    public void showSample1Screen(AndroidComponent androidComponent) {
        FragmentManager fragmentManager = androidComponent.getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.container, sample1ModuleInput.createFragment())
                .commit();
    }

    @Override
    public void showSample2Screen(AndroidComponent androidComponent) {
        FragmentManager fragmentManager = androidComponent.getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.container, sample2ModuleInput.createFragment())
                .commit();
    }

    @Override
    public void showDetailScreen(AndroidComponent androidComponent, SampleObject object) {
        Toast.makeText(androidComponent.getActivity(), "Not implemented yet", Toast.LENGTH_SHORT).show();
    }
}