package com.vershininds.mixture.logger

import android.annotation.SuppressLint
import android.util.Log


/**
 * Default [Logger] implementation based on android [Log].
 * Using by default in TaskExecutor
 */
@SuppressLint("LogNotTimber")
class LoggerDefault : Logger {
    val TAG: String = LoggerDefault::class.java.simpleName

    override fun log(msg: String) {
        Log.d(TAG, msg)
    }
}