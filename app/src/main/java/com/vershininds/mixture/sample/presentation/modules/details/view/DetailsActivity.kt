package com.vershininds.mixture.sample.presentation.modules.details.view

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.action.ViewAction
import com.vershininds.mixture.action.handle
import com.vershininds.mixture.dispatcher.subscribeView
import com.vershininds.mixture.router.manageBy
import com.vershininds.mixture.sample.R
import com.vershininds.mixture.sample.application.AppDelegate
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.databinding.ActivityDetailsBinding
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsVmContract
import com.vershininds.mixture.sample.presentation.modules.details.di.DetailsComponent
import com.vershininds.mixture.view.AndroidComponent
import com.vershininds.mixture.viewmodel.DataModel

class DetailsActivity : AppCompatActivity(), AndroidComponent {

    private lateinit var diComponent: DetailsComponent
    private val dispatcher: ActionDispatcher by lazy { diComponent.getDispatcher() }
    private var binding: ActivityDetailsBinding? = null

    companion object {

        val TAG: String = DetailsActivity::class.java.simpleName
        val KEY_DATA = TAG + "_KEY_DATA"


        fun createIntent(source: Activity, data: SampleObject): Intent {
            val intent = Intent(source, DetailsActivity::class.java)
            intent.putExtra(KEY_DATA, data)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDi(savedInstanceState)
        observeAction()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding?.btnFinish?.setOnClickListener {
            dispatcher.dispatch(DetailsVmContract.TypeUserAction.ClickOnFinishAction())
        }
    }

    override fun getActivity(): Activity = this

    private fun injectDi(savedInstanceState: Bundle?) {

        val args = intent.extras ?: return
        val sampleObject = args.getParcelable(KEY_DATA) as? SampleObject

        diComponent = AppDelegate.get()
                .presentationComponents()
                .detailsComponentFactory()
                .create(this, sampleObject!!)
                .apply {
                    inject(this@DetailsActivity)
                    getRouter().manageBy(this@DetailsActivity)
                    getViewModel().restoreInstanceState(savedInstanceState)
                }
    }

    private fun observeAction() {
        dispatcher.subscribeView(this) { action: ViewAction ->
            action.handle {
                when (it) {
                    is DetailsVmContract.TypeViewAction.DataAction -> handleDataAction(it)
                }
            }
        }
    }

    private fun handleDataAction(action: DetailsVmContract.TypeViewAction.DataAction) {
        val dataModel = action.data

        when (dataModel.state) {
            DataModel.State.LOADING -> {
                /*nop*/
            }
            DataModel.State.ERROR -> {
                /*nop*/
            }
            DataModel.State.DATA -> {
                val sampleObject = dataModel.data
                val txt = if (sampleObject == null) {
                    "object is null"
                } else {
                    "${sampleObject.name}\n${sampleObject.description}"
                }
                binding?.txtData?.text = txt
            }
        }
    }
}