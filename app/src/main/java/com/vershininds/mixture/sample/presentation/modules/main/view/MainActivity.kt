package com.vershininds.mixture.sample.presentation.modules.main.view

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vershininds.mixture.sample.R
import com.vershininds.mixture.sample.application.AppDelegate
import com.vershininds.mixture.sample.databinding.ActivityMainBinding
import com.vershininds.mixture.sample.presentation.modules.main.di.MainComponent
import com.vershininds.mixture.sample.presentation.modules.main.di.MainDiModule
import com.vershininds.mixture.sample.presentation.modules.main.contract.MainRouterContract
import com.vershininds.mixture.view.AndroidComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AndroidComponent {

    private var diComponent: MainComponent? = null
    private var mBinding: ActivityMainBinding? = null

    @Inject
    internal lateinit var router: MainRouterContract.Router

    companion object {

        val TAG = MainActivity::class.java.simpleName

        fun createIntent(source: Activity): Intent {
            return Intent(source, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDi()
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        if (savedInstanceState == null) router.showListScreen(this)
    }

    override fun getActivity(): Activity = this

    private fun injectDi() {
        diComponent = AppDelegate.get()
                .presentationComponents()
                .mainComponent(MainDiModule())
                .apply { inject(this@MainActivity) }
    }
}