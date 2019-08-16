package com.vershininds.mixture.mngtask.executor

import com.vershininds.mixture.mngtask.action.reactive.*
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.vershininds.mixture.mngtask.action.TaskFuture
import com.vershininds.mixture.mngtask.logger.Logger
import com.vershininds.mixture.mngtask.logger.LoggerDefault
import com.vershininds.mixture.mngtask.store.TaskStore
import com.vershininds.mixture.mngtask.store.TaskStoreRx
import com.vershininds.mixture.mngtask.strategy.GroupKey
import com.vershininds.mixture.mngtask.strategy.KillGroup
import com.vershininds.mixture.mngtask.strategy.KillMe
import com.vershininds.mixture.mngtask.strategy.SaveMe

/**
 * Implementation [TaskExecutor] for reactive source
 */
class TaskExecutorRx(
        private val scheduler: Scheduler = Schedulers.io(),
        private val logger: Logger = LoggerDefault()
) : TaskExecutor {

    private val store: TaskStore = TaskStoreRx()
    private var enabledLogTrace = false

    fun enableLogTrace(enable: Boolean) {
        enabledLogTrace = enable
    }

    @Synchronized
    override fun <T> execute(task: TaskFuture<T>, taskRes: (T) -> Unit, e: (Throwable) -> Unit) {
        if (task.groupStrategy == KillGroup) store.removeGroup(task.groupKey)
        return when {
            store.contains(task.groupKey, task.id) -> when (task.strategy) {
                KillMe -> {
                    stop(task.groupKey, task.id)
                    startExecution(task, taskRes, e)
                }
                SaveMe -> logger.printIfEnabled { log(task, "- Task duplicate") }
            }
            else -> startExecution(task, taskRes, e)
        }
    }

    override fun stop(groupKey: GroupKey, id: String) {
        store.remove(groupKey, id)
    }

    override fun cancelAll() {
        logger.printIfEnabled { log("Cancel all tasks") }
        store.removeAll()
    }

    override fun log(e: Throwable) {
        logger.logError(e)
    }

    private fun <T> startExecution(task: TaskFuture<T>, taskRes: (T) -> Unit, e: (Throwable) -> Unit) {
        logger.printIfEnabled { log(task, "- Task Started") }

        val removeFromMap = {
            store.remove(task.groupKey, task.id)
            logger.printIfEnabled { log(task, "- Task Finished") }
        }

        val actionOnDispose = {
            logger.printIfEnabled { log(task, "- Task Canceled") }
        }

        store.add(task.groupKey, task.id) {
            when (task) {
                is CompletableTaskFuture -> task.rxSource
                        .subscribeOn(scheduler)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(removeFromMap)
                        .doOnDispose(actionOnDispose)
                        .subscribe({}, e)
                is SingleTaskFuture -> task.rxSource
                        .subscribeOn(scheduler)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(removeFromMap)
                        .doOnDispose(actionOnDispose)
                        .subscribe({ taskRes(it) }, e)
                is MaybeTaskFuture -> task.rxSource
                        .subscribeOn(scheduler)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(removeFromMap)
                        .doOnDispose(actionOnDispose)
                        .subscribe({ taskRes(it) }, e)
                is ObservableTaskFuture -> task.rxSource
                        .subscribeOn(scheduler)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(removeFromMap)
                        .doOnDispose(actionOnDispose)
                        .subscribe({ taskRes(it) }, e)
                is FlowableTaskFuture -> task.rxSource
                        .subscribeOn(scheduler)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(removeFromMap)
                        .doOnCancel(actionOnDispose)
                        .subscribe({ taskRes(it) }, e)
                else -> throw IllegalArgumentException()
            }
        }
    }

    private fun Logger.printIfEnabled(print: Logger.() -> Unit) {
        if (enabledLogTrace) print()
    }
}