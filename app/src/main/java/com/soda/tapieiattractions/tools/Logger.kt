package com.soda.tapieiattractions.tools

import android.util.Log
import com.tencent.mmkv.BuildConfig

/**
 * 開發專用Log
 * 避免應用上架後 Log 資訊外洩
 */
object Logger {
    //使用BuildConfig.DEBUG判斷是否為開發模式
    private const val DEBUG_MODE: Boolean = BuildConfig.DEBUG

    fun i(tag: String, msg: String?) {
        if (DEBUG_MODE) Log.i(tag, msg?:"")
    }

    fun d(tag: String, msg: String?) {
        if (DEBUG_MODE) Log.d(tag, msg?:"")
    }

    fun e(tag: String, msg: String?) {
        if (DEBUG_MODE) Log.e(tag, msg?:"")
    }

    fun v(tag: String, msg: String?) {
        if (DEBUG_MODE) Log.v(tag, msg?:"")
    }

    fun w(tag: String, msg: String?) {
        if (DEBUG_MODE) Log.w(tag, msg?:"")
    }
}