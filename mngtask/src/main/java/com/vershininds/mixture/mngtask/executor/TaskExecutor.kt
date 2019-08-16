package com.vershininds.mixture.mngtask.executor

import com.vershininds.mixture.mngtask.action.TaskFuture
import com.vershininds.mixture.mngtask.strategy.DEFAULT_GROUP
import com.vershininds.mixture.mngtask.strategy.GroupKey


/**
 * That manages each submitted task([TaskFuture]), handles strategies
 */
interface TaskExecutor {
    /**
     * Starts the execution task([TaskFuture])
     *
     * @param task action for execution [TaskFuture].
     * @param taskRes task execution result, optional by default call stab func
     * @param e [Throwable], optional by default call method [log]
     */
    fun <TResult> execute(
            task: TaskFuture<TResult>,
            taskRes: (TResult) -> Unit = { resultHandlerStub() },
            e: (Throwable) -> Unit = { log(it) }
    )

    /**
     * Stop task([TaskFuture]) execution
     *
     * @param groupKey unique group tag.
     * @param id unique task tag
     */
    fun stop(groupKey: GroupKey = DEFAULT_GROUP, id: String)

    /**
     * Stop execution all tasks
     */
    fun cancelAll()

    /**
     * Default error handler, if you don't want handle errors
     */
    fun log(e: Throwable)

    /**
     * Default result handler, if you don't want handle task result
     */
    private fun resultHandlerStub() {}
}