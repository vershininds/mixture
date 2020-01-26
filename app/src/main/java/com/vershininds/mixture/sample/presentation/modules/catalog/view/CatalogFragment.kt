package com.vershininds.mixture.sample.presentation.modules.catalog.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.DefaultItemAnimator
import android.view.*
import com.vershininds.mixture.dispatcher.ActionDispatcher
import com.vershininds.mixture.action.ViewAction
import com.vershininds.mixture.action.handle
import com.vershininds.mixture.dispatcher.subscribeView
import com.vershininds.mixture.router.manageBy
import com.vershininds.mixture.sample.R
import com.vershininds.mixture.sample.application.AppDelegate
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.sample.databinding.FragmentCatalogBinding
import com.vershininds.mixture.sample.presentation.common.dbadapter.ListConfig
import com.vershininds.mixture.sample.presentation.common.dbadapter.adapter.BindableAdapter
import com.vershininds.mixture.sample.presentation.common.dbadapter.listener.ActionClickListener
import com.vershininds.mixture.sample.presentation.modules.catalog.contract.CatalogVmContract
import com.vershininds.mixture.sample.presentation.modules.catalog.di.CatalogComponent
import com.vershininds.mixture.sample.presentation.modules.catalog.view.adapters.ActionType
import com.vershininds.mixture.sample.presentation.modules.catalog.view.adapters.DataDelegate
import com.vershininds.mixture.sample.presentation.modules.catalog.viewmodel.CatalogVm
import com.vershininds.mixture.view.AndroidComponent
import com.vershininds.mixture.viewmodel.DataModel
import com.vershininds.mixture.viewmodel.ViewState


class CatalogFragment : Fragment(), AndroidComponent, ActionClickListener {


    companion object {
        fun newInstance(): Fragment {
            return CatalogFragment()
        }
    }

    private lateinit var diComponent: CatalogComponent
    private val vm: CatalogVm by lazy { diComponent.getViewModel() }
    private val dispatcher: ActionDispatcher by lazy { diComponent.getDispatcher() }
    private lateinit var binding: FragmentCatalogBinding
    private lateinit var adapter: BindableAdapter<List<SampleObject>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind(view)!!

        adapter = BindableAdapter(DataDelegate(this))

        ListConfig.Builder(adapter)
                .setItemAnimator(DefaultItemAnimator())
                .setHasFixedSize(true)
                .setDefaultDividerEnabled(true)
                .build(activity)
                .applyConfig(activity, binding.rvData)

        binding.btnRetry.setOnClickListener {
            dispatcher.dispatch(CatalogVmContract.TypeUserAction.RetryAction())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        diComponent = AppDelegate.get()
                .presentationComponents()
                .catalogComponentFactory()
                .create(this)
                .apply { inject(this@CatalogFragment) }
        diComponent.getRouter().manageBy(viewLifecycleOwner)
        vm.restoreInstanceState(savedInstanceState)

        observeAction()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(vm.saveInstanceState(outState))
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_catalog, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_error_catalog -> {
                dispatcher.dispatch(CatalogVmContract.TypeUserAction.LoadErrorListAction())
                return true
            }
            R.id.item_empty_catalog -> {
                dispatcher.dispatch(CatalogVmContract.TypeUserAction.LoadEmptyListAction())
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun getSupportFragmentManager(): FragmentManager? {
        return fragmentManager
    }

    override fun onActionClick(view: View, actionType: String, model: Any) {
        when (actionType) {
            ActionType.CLICK -> dispatcher.dispatch(
                    CatalogVmContract.TypeUserAction.ClickOnItemAction(model as SampleObject)
            )
        }
    }

    private fun observeAction() {
        dispatcher.subscribeView(viewLifecycleOwner) { action: ViewAction ->
            action.handle {
                when (it) {
                    is CatalogVmContract.TypeViewAction.DataAction -> handleDataAction(it)
/*                is CatalogVmContract.TypeViewAction.ErrorDialogWithCustomMessageAction ->
                    showErrorDialog(it.errorMsg)*/
                }
            }
        }
    }

    private fun handleDataAction(action: CatalogVmContract.TypeViewAction.DataAction) {
        val dataModel = action.data
        when (dataModel.state) {
            DataModel.State.LOADING -> {
                binding.state = ViewState.LOADING
            }
            DataModel.State.ERROR -> {
                binding.state = ViewState.ERROR
                binding.txtError.text = dataModel.error
            }
            DataModel.State.DATA -> {
                val data = dataModel.data
                if (data.isNullOrEmpty()) {
                    binding.state = ViewState.EMPTY
                } else {
                    binding.state = ViewState.DATA
                }
                adapter.items = data
                adapter.notifyDataSetChanged()
            }
        }
    }
}