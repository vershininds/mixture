package com.vershininds.mixture.sample.application;

import android.support.multidex.MultiDexApplication;

import com.vershininds.mixture.sample.application.assembly.AppComponent;
import com.vershininds.mixture.sample.application.assembly.AppModule;
import com.vershininds.mixture.sample.domain.assembly.BusinessComponent;
import com.vershininds.mixture.sample.domain.assembly.BusinessModule;
import com.vershininds.mixture.sample.presentation.FeatureDeclaration;
import com.vershininds.mixture.sample.presentation.PresentationComponents;
import com.vershininds.mixture.sample.application.assembly.DaggerAppComponent;
import com.vershininds.mixture.sample.domain.assembly.DaggerBusinessComponent;
import com.vershininds.mixture.sample.presentation.DaggerPresentationComponents;


public class AppDelegate extends MultiDexApplication {

    private static AppDelegate mInstance;

    private BusinessComponent mBusinessComponent;
    private PresentationComponents mPresentationComponents;

    public static AppDelegate get() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);

        mInstance = this;

        injectDependencies();
    }

    public BusinessComponent businessComponent() {
        return mBusinessComponent;
    }

    public PresentationComponents presentationComponents() {
        return mPresentationComponents;
    }

    private void injectDependencies() {
        mBusinessComponent = DaggerBusinessComponent.builder()
                .businessModule(new BusinessModule(this))
                .build();

        mPresentationComponents = DaggerPresentationComponents.builder()
                .businessComponent(mBusinessComponent)
                .featureDeclaration(new FeatureDeclaration()).build();

        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .presentationComponents(mPresentationComponents)
                .build();
    }
}
