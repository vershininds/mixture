package com.vershininds.mixture.sample.presentation.modules.main.di

import com.vershininds.mixture.sample.presentation.assembly.PerFeatureScope
import com.vershininds.mixture.sample.presentation.modules.main.contract.MainRouterContract
import com.vershininds.mixture.sample.presentation.modules.main.router.MainRouter
import dagger.Module
import dagger.Provides

@Module
class MainDiModule {

    @PerFeatureScope
    @Provides
    fun providesRouter(router: MainRouter): MainRouterContract.Router = router
}