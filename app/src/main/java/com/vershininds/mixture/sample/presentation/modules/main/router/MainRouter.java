package com.vershininds.mixture.sample.presentation.modules.main.router;

import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.vershininds.mixture.router.AbstractRouter;
import com.vershininds.mixture.sample.R;
import com.vershininds.mixture.sample.data.SampleObject;
import com.vershininds.mixture.sample.presentation.modules.main.contract.MainRouterContract;
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.SampleRx2ModuleInput;
import com.vershininds.mixture.sample.presentation.modules.rxinteractor.SampleRxModuleInput;
import com.vershininds.mixture.view.AndroidComponent;

import javax.inject.Inject;

public class MainRouter extends AbstractRouter<MainRouterContract.Presenter>
        implements MainRouterContract.Router {

    private final SampleRxModuleInput sampleRxModuleInput;
    private final SampleRx2ModuleInput sampleRx2ModuleInput;

    @Inject
    public MainRouter(SampleRxModuleInput sampleRxModuleInput, SampleRx2ModuleInput sampleRx2ModuleInput) {
        this.sampleRxModuleInput = sampleRxModuleInput;
        this.sampleRx2ModuleInput = sampleRx2ModuleInput;
    }

    @Override
    public void showSample1Screen(AndroidComponent androidComponent) {
        FragmentManager fragmentManager = androidComponent.getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.container, sampleRxModuleInput.createFragment())
                .commit();
    }

    @Override
    public void showSample2Screen(AndroidComponent androidComponent) {
        FragmentManager fragmentManager = androidComponent.getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.container, sampleRx2ModuleInput.createFragment())
                .commit();
    }

    @Override
    public void showDetailScreen(AndroidComponent androidComponent, SampleObject object) {
        Toast.makeText(androidComponent.getActivity(), "Not implemented yet", Toast.LENGTH_SHORT).show();
    }
}