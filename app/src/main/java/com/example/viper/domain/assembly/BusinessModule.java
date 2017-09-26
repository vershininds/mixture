package com.example.viper.domain.assembly;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import com.example.viper.domain.services.ServiceMaker;

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
