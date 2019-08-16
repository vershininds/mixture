package com.vershininds.mixture.sample.presentation.modules.details.view

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vershininds.mixture.action.ViewAction
import com.vershininds.mixture.action.handle
import com.vershininds.mixture.action.subscribe
import com.vershininds.mixture.router.MviRouter
import com.vershininds.mixture.sample.R
import com.vershininds.mixture.sample.application.AppDelegate
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.databinding.ActivityDetailsBinding
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsRouterContract
import com.vershininds.mixture.sample.presentation.modules.details.contract.DetailsVmContract
import com.vershininds.mixture.sample.presentation.modules.details.di.DetailsComponent
import com.vershininds.mixture.sample.presentation.modules.details.di.DetailsDiModule
import com.vershininds.mixture.sample.presentation.modules.details.viewmodel.DetailsVm
import com.vershininds.mixture.sample.presentation.modules.details.viewmodel.DetailsVmFactory
import com.vershininds.mixture.view.AndroidComponent
import com.vershininds.mixture.viewmodel.DataModel
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), AndroidComponent {

    private lateinit var diComponent: DetailsComponent
    private lateinit var vm: DetailsVm
    private var binding: ActivityDetailsBinding? = null

    @Inject
    internal lateinit var router: MviRouter<DetailsRouterContract.TypeRouterAction>

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

        diComponent = AppDelegate.get()
                .presentationComponents()
                .detailsComponent(DetailsDiModule())
                .apply { inject(this@DetailsActivity) }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        val args = intent.extras ?: return
        val sampleObject = args.getParcelable(KEY_DATA) as SampleObject

        val vmFactory = DetailsVmFactory(diComponent.getInteractor(), sampleObject)

        vm = ViewModelProviders.of(this, vmFactory)
                .get(DetailsVm::class.java)
                .apply { restoreInstanceState(savedInstanceState) }

        observeAction(vm)

        binding?.btnFinish?.setOnClickListener { vm.onClickFinish() }
    }

    override fun getActivity(): Activity = this

    private fun observeAction(model: DetailsVm) {
        model.actionDispatcher.subscribe(this) { action ->
            when (action) {
                is ViewAction -> handleViewAction(action)
                is DetailsRouterContract.TypeRouterAction -> action.handle {
                    router.actionHandler(this, it)
                }
            }
        }
    }

    private fun handleViewAction(action: ViewAction) {
        action.handle {
            when (it) {
                is DetailsVmContract.TypeViewAction.DataAction -> handleDataAction(it)
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