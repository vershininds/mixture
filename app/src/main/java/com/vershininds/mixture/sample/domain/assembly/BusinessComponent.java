package com.vershininds.mixture.sample.domain.assembly;


import dagger.Component;

import com.vershininds.mixture.sample.domain.services.ServiceMaker;

@PerBusinessLayerScope
@Component(modules = BusinessModule.class)
public interface BusinessComponent {

    void inject(ServiceMaker serviceMaker);

    ServiceMaker getServiceMaker();
}
