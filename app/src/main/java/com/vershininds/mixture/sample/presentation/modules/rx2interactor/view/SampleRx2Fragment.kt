package com.vershininds.mixture.sample.presentation.modules.rx2interactor.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.DefaultItemAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vershininds.mixture.action.ViewAction
import com.vershininds.mixture.action.handle
import com.vershininds.mixture.action.subscribe
import com.vershininds.mixture.router.MviRouter
import com.vershininds.mixture.sample.R
import com.vershininds.mixture.sample.application.AppDelegate
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.databinding.FragmentSampleRx2Binding
import com.vershininds.mixture.sample.presentation.common.dbadapter.ListConfig
import com.vershininds.mixture.sample.presentation.common.dbadapter.adapter.BindableAdapter
import com.vershininds.mixture.sample.presentation.common.dbadapter.listener.ActionClickListener
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2RouterContract
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.contract.SampleRx2VmContract
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.di.SampleRx2Component
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.di.SampleRx2DiModule
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.view.adapters.ActionType
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.view.adapters.DataDelegate
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.viewmodel.SampleRx2Vm
import com.vershininds.mixture.sample.presentation.modules.rx2interactor.viewmodel.SampleRx2VmFactory
import com.vershininds.mixture.view.AndroidComponent
import com.vershininds.mixture.viewmodel.DataModel
import javax.inject.Inject

class SampleRx2Fragment : Fragment(), AndroidComponent, ActionClickListener {


    companion object {

        val TAG = SampleRx2Fragment::class.java.simpleName

        fun newInstance(): Fragment {
            return SampleRx2Fragment()
        }
    }

    private lateinit var diComponent: SampleRx2Component
    private lateinit var vm: SampleRx2Vm
    private lateinit var binding: FragmentSampleRx2Binding
    private lateinit var adapter: BindableAdapter<List<SampleObject>>

    @Inject
    internal lateinit var router: MviRouter<SampleRx2RouterContract.TypeRouterAction>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        diComponent = AppDelegate.get()
                .presentationComponents()
                .sample2Component(SampleRx2DiModule())
                .apply { inject(this@SampleRx2Fragment) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_sample_rx2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind<FragmentSampleRx2Binding>(view)!!

        adapter = BindableAdapter(DataDelegate(this))

        ListConfig.Builder(adapter)
                .setItemAnimator(DefaultItemAnimator())
                .setHasFixedSize(true)
                .setDefaultDividerEnabled(true)
                .build(activity)
                .applyConfig(activity, binding.rvData)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val vmFactory = SampleRx2VmFactory(diComponent.getInteractor())

        vm = ViewModelProviders.of(this, vmFactory)
                .get(SampleRx2Vm::class.java)
                .apply { restoreInstanceState(savedInstanceState) }

        observeAction(vm)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(vm.saveInstanceState(outState))
    }

    override fun getSupportFragmentManager(): FragmentManager? {
        return fragmentManager
    }

    override fun onActionClick(view: View, actionType: String, model: Any) {
        when (actionType) {
            ActionType.CLICK -> vm.clickOnItem(model as SampleObject)
        }
    }

    private fun observeAction(model: SampleRx2Vm) {
        model.actionDispatcher.subscribe(viewLifecycleOwner) { action ->
            when (action) {
                is ViewAction -> handleViewAction(action)
                is SampleRx2RouterContract.TypeRouterAction -> handleRouterAction(action)
            }
        }
    }

    private fun handleViewAction(action: ViewAction) {
        action.handle {
            when (it) {
                is SampleRx2VmContract.TypeViewAction.DataAction -> handleDataAction(it)
/*                is SampleRx2VmContract.TypeViewAction.ErrorDialogWithCustomMessageAction ->
                    showErrorDialog(it.errorMsg)*/
            }
        }
    }

    private fun handleDataAction(action: SampleRx2VmContract.TypeViewAction.DataAction) {
        val dataModel = action.data

        val state = dataModel.state
        binding.state = state

        when (state) {
            DataModel.State.LOADING -> { /*nop*/
            }
            DataModel.State.ERROR -> {
                binding.txtError.text = dataModel.error
            }
            DataModel.State.DATA -> {
                adapter.items = dataModel.data
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun handleRouterAction(action: SampleRx2RouterContract.TypeRouterAction) {
        action.handle { router.actionHandler(this, it) }
    }
}