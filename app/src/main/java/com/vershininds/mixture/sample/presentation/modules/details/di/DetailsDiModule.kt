package com.vershininds.mixture.sample.presentation.modules.details.di

import android.arch.lifecycle.ViewModelProviders
import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.router.MxtRouter2
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.domain.services.ServiceMaker
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.details.router.DetailsRouter
import com.vershininds.mixture.sample.presentation.modules.details.view.DetailsActivity
import com.vershininds.mixture.sample.presentation.modules.details.viewmodel.DetailsVm
import dagger.Module
import dagger.Provides

@Module
class DetailsDiModule {

    @PerFeatureScope
    @Provides
    fun providesViewModel(detailsPermComponent: DetailsPermComponent): DetailsVm {
        return detailsPermComponent.getViewModel()
    }

    @PerFeatureScope
    @Provides
    fun providesDispatcher(detailsPermComponent: DetailsPermComponent): ActionDispatcher {
        return detailsPermComponent.getDispatcher()
    }

    @PerFeatureScope
    @Provides
    fun providesRouter(activity: DetailsActivity, dispatcher: ActionDispatcher): MxtRouter2 =
            DetailsRouter(activity, dispatcher)


    @PerFeatureScope
    @Provides
    fun providesPermComponent(
            activity: DetailsActivity,
            serviceMaker: ServiceMaker,
            sampleObject: SampleObject
    ): DetailsPermComponent {
        val diPermComponent = DaggerDetailsPermComponent.builder()
                .serviceManager(serviceMaker)
                .data(sampleObject)
                .build()

        return ViewModelProviders
                .of(activity, DetailsHolderFactory(diPermComponent))
                .get(DetailsHolder::class.java)
                .component
    }
}