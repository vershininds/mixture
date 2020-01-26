package com.vershininds.mixture.dispatcher

import android.arch.lifecycle.LifecycleOwner
import com.vershininds.mixture.action.*


inline fun ActionDispatcher.subscribe(owner: LifecycleOwner, crossinline action: (action: RetentiveAction) -> Unit) {
    subscribe(owner, object : ActionDispatcher.Subscriber {
        override fun onAction(action: RetentiveAction) {
            action(action)
        }
    })
}

inline fun ActionDispatcher.subscribe(tag: String, crossinline action: (action: RetentiveAction) -> Unit) {
    subscribe(tag, object : ActionDispatcher.Subscriber {
        override fun onAction(action: RetentiveAction) {
            action(action)
        }
    })
}


interface ViewSubscriber : ActionDispatcher.Subscriber {
    fun onViewAction(action: ViewAction)
}

inline fun ActionDispatcher.subscribeView(owner: LifecycleOwner, crossinline viewAction: (action: ViewAction) -> Unit) {
    subscribe(owner, object : ViewSubscriber {
        override fun onViewAction(action: ViewAction) {
            viewAction(action)
        }

        override fun onAction(action: RetentiveAction) {
            if (action is ViewAction) onViewAction(action)
        }
    })
}

interface VmSubscriber : ActionDispatcher.Subscriber {
    fun onUserAction(action: UserAction)
    fun onInteractorAction(action: InteractorAction)
    fun onRouterAction(action: RouterAction)
}

inline fun ActionDispatcher.subscribeVm(tag: String = "ViewModel",
                                        crossinline userAction: (action: UserAction) -> Unit = { resultHandlerStub() },
                                        crossinline interactorAction: (action: InteractorAction) -> Unit = { resultHandlerStub() },
                                        crossinline routerAction: (action: RouterAction) -> Unit = { resultHandlerStub() },
                                        crossinline action: (action: RetentiveAction) -> Unit = { resultHandlerStub() }
) {
    subscribe(tag, object : VmSubscriber {
        override fun onUserAction(action: UserAction) {
            userAction(action)
        }

        override fun onInteractorAction(action: InteractorAction) {
            interactorAction(action)
        }

        override fun onRouterAction(action: RouterAction) {
            routerAction(action)
        }

        override fun onAction(action: RetentiveAction) {
            when (action) {
                is UserAction -> onUserAction(action)
                is InteractorAction -> onInteractorAction(action)
                is RouterAction -> onRouterAction(action)
            }

            action(action)
        }
    })
}

interface InteractorSubscriber : ActionDispatcher.Subscriber {
    fun onInteractorAction(action: InteractorAction)
}

inline fun ActionDispatcher.subscribeInteractor(tag: String = "Interactor", crossinline interactorAction: (action: InteractorAction) -> Unit) {
    subscribe(tag, object : InteractorSubscriber {
        override fun onInteractorAction(action: InteractorAction) {
            interactorAction(action)
        }

        override fun onAction(action: RetentiveAction) {
            if (action is InteractorAction) onInteractorAction(action)
        }
    })
}

interface RouterSubscriber : ActionDispatcher.Subscriber {
    fun onRouterAction(action: RouterAction)
}

inline fun ActionDispatcher.subscribeRouter(tag: String = "Router", crossinline routerAction: (action: RouterAction) -> Unit) {
    subscribe(tag, object : RouterSubscriber {
        override fun onRouterAction(action: RouterAction) {
            routerAction(action)
        }

        override fun onAction(action: RetentiveAction) {
            if (action is RouterAction) onRouterAction(action)
        }
    })
}

/**
 * Default result handler, if you don't want handle result
 */
fun resultHandlerStub() {}