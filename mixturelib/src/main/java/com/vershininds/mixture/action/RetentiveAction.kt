package com.vershininds.mixture.action

/**
 * Action with mark: event was processed or not
 */
open class RetentiveAction {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Mark action as handled and prevents its use again.
     */
    fun mark(): RetentiveAction? {
        return if (hasBeenHandled) null
        else {
            hasBeenHandled = true
            this
        }
    }
}

/**
 * Calls the specified function [block] with `this` value as its argument, if action was not handled.
 * Returns `null` value if action was handled or `this`, if it doesn't.
 */
inline fun <T : RetentiveAction> T.handle(block: (T) -> Unit): T? {
    return if (this.mark() != null) {
        block(this)
        this
    } else null
}