package com.vershininds.mixture.sample.domain.assembly;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

import com.vershininds.mixture.sample.domain.services.ServiceMaker;

@Module
public class BusinessModule {

    private Context mContext;

    public BusinessModule(Context context) {
        this.mContext = context;
    }

    @PerBusinessLayerScope
    @Provides
    ServiceMaker provideServiceMaker() {
        return new ServiceMaker();
    }
}
