package com.example.viper.application;

import android.app.Application;

import com.example.viper.application.assembly.AppComponent;
import com.example.viper.application.assembly.AppModule;
import com.example.viper.application.assembly.DaggerAppComponent;
import com.example.viper.domain.assembly.BusinessComponent;
import com.example.viper.domain.assembly.BusinessModule;
import com.example.viper.domain.assembly.DaggerBusinessComponent;
import com.example.viper.presentation.DaggerPresentationComponents;
import com.example.viper.presentation.FeatureDeclaration;
import com.example.viper.presentation.PresentationComponents;


public class AppDelegate extends Application {

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
