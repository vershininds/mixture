package com.vershininds.mixture.action

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Looper
import android.support.annotation.MainThread
import com.vershininds.mixture.action.ActionDispatcher.Subscriber
import java.util.*

/**
 * ActionDispatcher has a similar ideology with [LiveData].
 * Unlike the [LiveData] it has one subscriber and stores multiple events(actions) in queue.
 *
 * Work with action [RetentiveAction] if subscriber not mark action as has been handled,
 * this action will be resend when subscriber will become active.
 *
 * Work with life cycle is the same with LiveData.
 * This means that an [Subscriber] can be added in a pair with a [LifecycleOwner], and
 * this subscriber will be notified about modifications of the wrapped data only if the paired
 * LifecycleOwner is in active state. LifecycleOwner is considered as active, if its state is
 * [Lifecycle.State.STARTED] or [Lifecycle.State.RESUMED].
 *
 * An subscriber added with a Lifecycle will be automatically removed if the corresponding
 * Lifecycle moves to [Lifecycle.State.DESTROYED] state. This is especially useful for
 * activities and fragments where they can safely observe LiveData and not worry about leaks:
 * they will be instantly unsubscribed when they are destroyed.
 */
class ActionDispatcher {

    /**
     * A simple callback that can receive from [ActionDispatcher].
     */
    interface Subscriber {
        /**
         * Called when the action is dispatch.
         * @param RetentiveAction  The new action
         */
        fun onAction(action: RetentiveAction)
    }

    // how many subscribers are in active state
    private var activeCount = 0
    private var subWrapper : SubscriberWrapper? = null
    // exclude chatter subscriber
    private var storeVersion = -1

    private val storeRepeatedAction = mutableMapOf<Class<out RetentiveAction>, RetentiveAction>()
    private val actionQueue = ArrayDeque<RetentiveAction>()


    /**
     * Dispatch the value. If there are active subscribers, the value will be dispatched to them.
     * Else the action will be stored in queue and dispatch when the active subscriber appears.
     *
     * If subscriber not mark action as has been handled,
     * this action will be resend when subscriber will become active.
     *
     * This method must be called from the main thread.
     *
     * @param action The new action
     */
    @MainThread
    fun dispatch(action: RetentiveAction){
        assertMainThread("dispatch")

        actionQueue.push(action)
        dispatchingAction()
    }


    /**
     * Adds the given subscriber within the lifespan of the given owner.
     * The events are dispatched on the main thread. If ActionDispatcher already has data
     * set, it will be delivered to the subscriber.
     *
     *  The subscriber will only receive events if the owner is in [Lifecycle.State.STARTED]
     * or [Lifecycle.State.RESUMED] state (active).
     *
     * If the owner moves to the [Lifecycle.State.DESTROYED] state, the subscriber will
     * automatically be removed.
     *
     * When action dispatched while the [owner] is not active, it will not receive any actions.
     * If it becomes active again, it will receive the action.
     *
     * Dispatcher keeps a strong reference to the subscriber and the owner as long as the
     * given LifecycleOwner is not destroyed. When it is destroyed, Dispatcher removes references to
     * the observer &amp; the owner.
     *
     * If the given owner is already in [Lifecycle.State.DESTROYED] state, Dispatcher
     * ignores the call.
     *
     * If the given owner, subscriber is already contains, the call is ignored.
     * If the subscriber is already with another owner, ActionDispatcher throws an
     * [IllegalArgumentException].
     *
     * @param owner    The LifecycleOwner which controls the subscriber
     * @param subscriber The subscriber that will receive the events
     */
    @MainThread
    fun subscribe(owner: LifecycleOwner, subscriber : Subscriber) {
        assertMainThread("subscribe")

        if (owner.lifecycle.currentState == Lifecycle.State.DESTROYED) {
            // ignore
            return
        }

        val lcObserver = LifecycleBoundObserver(owner, subscriber)
        val existing = subWrapper
        if (existing == null) subWrapper = lcObserver

        if (existing != null && !existing.isAttachedTo(owner)) {
            throw IllegalArgumentException("Cannot add the same subscriber with different lifecycles")
        }
        if (existing != null) {
            return
        }

        owner.lifecycle.addObserver(lcObserver)
    }

    /**
     * Remove current subscriber.
     */
    @MainThread
    fun removeSubscriber() {
        assertMainThread("removeSubscriber")

        subWrapper?.let {
            it.detachSubscriber()
            it.activeStateChanged(false)
        }
        subWrapper = null
    }

    /**
     * Called when the subscriber active
     *
     * This callback can be used to know that this Dispatcher is being used thus should be kept
     * up to date.
     */
    fun onActive() {

    }

    /**
     * Called when the subscriber not active
     *
     * This does not mean that there are no subscribers left, there may still be subscribers but their
     * lifecycle states aren't [Lifecycle.State.STARTED] or [Lifecycle.State.RESUMED]
     * (like an Activity in the back stack).
     *
     * You can check if there are subscriber via [hasSubscriber].
     */
    fun onInactive() {

    }

    /**
     * Returns true if this ActionDispatcher has subscribers.
     *
     * @return true if this ActionDispatcher has subscribers
     */
    fun hasSubscriber(): Boolean {
        return subWrapper != null
    }

    /**
     * Returns true if this ActionDispatcher has active subscriber.
     *
     * @return true if this ActionDispatcher has active subscriber
     */
    fun hasActiveSubscriber(): Boolean {
        return activeCount > 0
    }


    private fun dispatchingAction() {
        dispatchRepeatedAction()
        dispatchQueueAction()
    }

    private fun dispatchRepeatedAction(){
        val storeIterator = storeRepeatedAction.iterator()
        while (storeIterator.hasNext()) {
            val action = storeIterator.next().value
            if(action.hasBeenHandled){
                storeIterator.remove()
                continue
            }
            if (!notify(action)) return
        }
    }

    private fun dispatchQueueAction(){
        while (actionQueue.isNotEmpty()) {
            val action = actionQueue.pop()
            if (!action.hasBeenHandled) putActionInStore(action)

            if (!notify(action)) return
        }
    }

    private fun putActionInStore(action: RetentiveAction){
        storeVersion++
        storeRepeatedAction[action.javaClass] = action
    }

    private fun notify(action: RetentiveAction) : Boolean {
        return notifySubscriber(subWrapper, action)
    }

    private fun notifySubscriber(subWrapper: SubscriberWrapper?, action: RetentiveAction) : Boolean {
        if (subWrapper == null || !subWrapper.isActive) {
            return false
        }
        // Check latest state be dispatch. Maybe it changed state but we didn't get the event yet.
        //
        // we still first check subWrapper.active to keep it as the entrance for events. So even if
        // the subWrapper moved to an active state, if we've not received that event, we better not
        // notify for a more predictable notification order.
        if (!subWrapper.shouldBeActive()) {
            subWrapper.activeStateChanged(false)
            return false
        }

        if (subWrapper.lastVersion >= storeVersion) {
            return false
        }
        subWrapper.lastVersion = storeVersion

        subWrapper.subscriber.onAction(action)
        return true
    }


    private inner class LifecycleBoundObserver(val lcOwner: LifecycleOwner, subscriber: Subscriber) : SubscriberWrapper(subscriber), LifecycleObserver {

        override fun shouldBeActive(): Boolean {
            return lcOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
        }

        override fun isAttachedTo(owner: LifecycleOwner): Boolean {
            return lcOwner === owner
        }

        override fun detachSubscriber() {
            super.detachSubscriber()
            lcOwner.lifecycle.removeObserver(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        fun onStateChanged() {
            if (lcOwner.lifecycle.currentState == Lifecycle.State.DESTROYED) {
                removeSubscriber()
                return
            }
            activeStateChanged(shouldBeActive())
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            lcOwner.lifecycle.removeObserver(this)
        }
    }

    private abstract inner class SubscriberWrapper constructor(val subscriber: Subscriber) {
        var isActive: Boolean = false
        var lastVersion = -1 //protect from chatter

        abstract fun shouldBeActive(): Boolean

        open fun isAttachedTo(owner: LifecycleOwner): Boolean {
            return false
        }

        open fun detachSubscriber() {}

        fun activeStateChanged(newActive: Boolean) {
            if (newActive == isActive) {
                return
            }
            // immediately set active state, so we'd never dispatch anything to inactive
            // owner
            isActive = newActive
            val wasInactive = this@ActionDispatcher.activeCount == 0
            this@ActionDispatcher.activeCount += if (isActive) 1 else -1
            if (wasInactive && isActive) {
                onActive()
            }
            if (this@ActionDispatcher.activeCount == 0 && !isActive) {
                onInactive()
            }
            if (isActive) {
                dispatchingAction()
            }
        }
    }

    private fun assertMainThread(methodName: String) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw IllegalStateException("Cannot invoke " + methodName + " on a background"
                    + " thread")
        }
    }
}

inline fun ActionDispatcher.subscribe(owner: LifecycleOwner, crossinline action: (action: RetentiveAction) -> Unit) {
    subscribe(owner, object : ActionDispatcher.Subscriber {
        override fun onAction(action: RetentiveAction) {
            action(action)
        }

    })
}