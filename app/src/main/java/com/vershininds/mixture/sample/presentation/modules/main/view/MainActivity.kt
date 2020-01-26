package com.vershininds.mixture.sample.presentation.modules.main.view

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vershininds.mixture.router.manageBy
import com.vershininds.mixture.sample.R
import com.vershininds.mixture.sample.application.AppDelegate
import com.vershininds.mixture.sample.databinding.ActivityMainBinding
import com.vershininds.mixture.sample.presentation.modules.main.di.MainComponent
import com.vershininds.mixture.view.AndroidComponent

class MainActivity : AppCompatActivity(), AndroidComponent {

    private lateinit var diComponent: MainComponent
    private var mBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDi(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun getActivity(): Activity = this

    private fun injectDi(savedInstanceState: Bundle?) {
        diComponent = AppDelegate.get()
                .presentationComponents()
                .mainComponentFactory()
                .create(this)
                .apply {
                    inject(this@MainActivity)
                    getRouter().manageBy(this@MainActivity)
                    getViewModel().restoreInstanceState(savedInstanceState)
                }
    }
}