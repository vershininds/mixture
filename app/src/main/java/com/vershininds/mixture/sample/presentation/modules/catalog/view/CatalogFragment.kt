package com.vershininds.mixture.sample.presentation.modules.catalog.view

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
import com.vershininds.mixture.sample.databinding.FragmentCatalogBinding
import com.vershininds.mixture.sample.presentation.common.dbadapter.ListConfig
import com.vershininds.mixture.sample.presentation.common.dbadapter.adapter.BindableAdapter
import com.vershininds.mixture.sample.presentation.common.dbadapter.listener.ActionClickListener
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogRouterContract
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogVmContract
import com.vershininds.mixture.sample.presentation.modules.catalog.di.CatalogComponent
import com.vershininds.mixture.sample.presentation.modules.catalog.di.CatalogDiModule
import com.vershininds.mixture.sample.presentation.modules.catalog.view.adapters.ActionType
import com.vershininds.mixture.sample.presentation.modules.catalog.view.adapters.DataDelegate
import com.vershininds.mixture.sample.presentation.modules.catalog.viewmodel.CatalogVm
import com.vershininds.mixture.sample.presentation.modules.catalog.viewmodel.CatalogVmFactory
import com.vershininds.mixture.view.AndroidComponent
import com.vershininds.mixture.viewmodel.DataModel
import javax.inject.Inject

class CatalogFragment : Fragment(), AndroidComponent, ActionClickListener {


    companion object {

        val TAG = CatalogFragment::class.java.simpleName

        fun newInstance(): Fragment {
            return CatalogFragment()
        }
    }

    private lateinit var diComponent: CatalogComponent
    private lateinit var vm: CatalogVm
    private lateinit var binding: FragmentCatalogBinding
    private lateinit var adapter: BindableAdapter<List<SampleObject>>

    @Inject
    internal lateinit var router: MviRouter<CatalogRouterContract.TypeRouterAction>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        diComponent = AppDelegate.get()
                .presentationComponents()
                .catalogComponent(CatalogDiModule())
                .apply { inject(this@CatalogFragment) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_catalog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind<FragmentCatalogBinding>(view)!!

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

        val vmFactory = CatalogVmFactory(diComponent.getInteractor())

        vm = ViewModelProviders.of(this, vmFactory)
                .get(CatalogVm::class.java)
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

    private fun observeAction(model: CatalogVm) {
        model.actionDispatcher.subscribe(viewLifecycleOwner) { action ->
            when (action) {
                is ViewAction -> handleViewAction(action)
                is CatalogRouterContract.TypeRouterAction -> handleRouterAction(action)
            }
        }
    }

    private fun handleViewAction(action: ViewAction) {
        action.handle {
            when (it) {
                is CatalogVmContract.TypeViewAction.DataAction -> handleDataAction(it)
/*                is CatalogVmContract.TypeViewAction.ErrorDialogWithCustomMessageAction ->
                    showErrorDialog(it.errorMsg)*/
            }
        }
    }

    private fun handleDataAction(action: CatalogVmContract.TypeViewAction.DataAction) {
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

    private fun handleRouterAction(action: CatalogRouterContract.TypeRouterAction) {
        action.handle { router.actionHandler(this, it) }
    }
}