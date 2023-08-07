package com.soda.tapieiattractions

import android.app.Application
import android.content.Context
import android.util.Log
import com.tencent.mmkv.MMKV

class TpaApplication : Application() {

    companion object{
        private var instance:TpaApplication? = null

        fun getAppContext():Context{
            return instance!!.applicationContext
        }
        fun getApp():TpaApplication{
            return instance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        initSharedPreference()
    }

    /**
     * 初始化MMKV (SharedPreference)
     */
    private fun initSharedPreference(){
        MMKV.initialize(this)
    }
}