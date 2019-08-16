package com.vershininds.mixture.mngtask.logger

import com.vershininds.mixture.mngtask.action.Task


/**
 * Logger for trace [Task] execution in [TaskExecutor]
 */
interface Logger {
    fun logError(e: Throwable)
    /**
     * @param task [Task].
     * @param msg additional information about [Task] execution.
     */
    fun log(task: Task, msg: String)

    fun log(msg: String)
}