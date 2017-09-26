package com.vershininds.mixture.view;

import com.vershininds.mixture.presenter.MvpPresenter;
import com.vershininds.mixture.viewmodel.MvpViewModel;


public abstract class ViewLogicDelegate<VM extends MvpViewModel, P extends MvpPresenter<VM>> {

//    private static final String VM_KEY = ViewLogicDelegate.class.getSimpleName() + "_VM";
//
//    private P presenter;
//    private VM viewModel;
//
//    protected abstract void injectDi();
//    protected abstract P createPresenter();
//
//    protected P getPresenter() {
//        return presenter;
//    }
//    protected VM getViewModel() {
//        return viewModel;
//    }
//
//    protected void onCreate(@Nullable Bundle savedInstanceState, AndroidComponent androidComponent) {
//        injectDi();
//
//        if (null == savedInstanceState) {
//            viewModel = createViewModel();
//            presenter = createPresenter();
//        } else {
//            viewModel = Parcels.unwrap(savedInstanceState.getParcelable(VM_KEY));
//            presenter = PresenterStorage.getInstance().evict(getViewModel().getId());
//            if (presenter == null) {
//                presenter = createPresenter();
//            } else {
//                VM presenterVm = presenter.getViewModel();
//                if (presenterVm != null) viewModel = presenterVm;
//            }
//        }
//
//        presenter.attachView(getViewModel(), androidComponent);
//    }
//
//    protected void onStop(boolean isChangingConfigurations) {
//        presenter.detachView();
//
//        if(isChangingConfigurations) {
//            PresenterStorage.getInstance().save(getViewModel().getId(), presenter);
//        } else {
//            presenter.destroy();
//        }
//    }
//
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putParcelable(VM_KEY, Parcels.wrap(getViewModel()));
//    }
}
