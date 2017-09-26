package com.example.viper.domain.assembly;


import dagger.Component;
import com.example.viper.domain.services.ServiceMaker;

@PerBusinessLayerScope
@Component(modules = BusinessModule.class)
public interface BusinessComponent {

    void inject(ServiceMaker serviceMaker);

    ServiceMaker getServiceMaker();
}
