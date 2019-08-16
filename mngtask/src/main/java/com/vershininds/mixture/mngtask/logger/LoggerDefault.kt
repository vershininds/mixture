package com.vershininds.mixture.mngtask.logger

import android.annotation.SuppressLint
import android.util.Log
import com.vershininds.mixture.mngtask.action.Task

/**
 * Default [Logger] implementation based on android [Log].
 * Using by default in TaskExecutor
 */
@SuppressLint("LogNotTimber")
class LoggerDefault : Logger {
    val TAG: String = LoggerDefault::class.java.simpleName

    override fun logError(e: Throwable) {
        Log.e(TAG, e.localizedMessage)
    }

    override fun log(task: Task, msg: String) {
        log("${task.id} $msg " + System.currentTimeMillis())
    }

    override fun log(msg: String) {
        Log.d(TAG, msg)
    }
}