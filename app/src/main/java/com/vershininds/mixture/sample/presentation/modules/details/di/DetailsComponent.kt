package com.vershininds.mixture.sample.presentation.modules.details.di

import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.router.MxtRouter2
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.details.view.DetailsActivity
import com.vershininds.mixture.sample.presentation.modules.details.viewmodel.DetailsVm
import dagger.BindsInstance
import dagger.Subcomponent

@PerFeatureScope
@Subcomponent(modules = [DetailsDiModule::class])
interface DetailsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
                @BindsInstance activity: DetailsActivity,
                @BindsInstance sampleObject: SampleObject
        ): DetailsComponent
    }

    fun getViewModel(): DetailsVm
    fun getDispatcher(): ActionDispatcher
    fun getRouter(): MxtRouter2

    fun inject(detailsActivity: DetailsActivity)
}