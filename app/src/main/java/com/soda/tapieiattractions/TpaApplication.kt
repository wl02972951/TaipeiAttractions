package com.soda.tapieiattractions

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import com.blankj.utilcode.util.LanguageUtils
import com.soda.tapieiattractions.enumClass.SystemLanguage
import com.soda.tapieiattractions.sharedPreference.SystemSP
import com.tencent.mmkv.MMKV
import java.util.Locale

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
        initSystemLanguage()
    }




    /**
     * 初始化MMKV (SharedPreference)
     */
    private fun initSharedPreference(){
        MMKV.initialize(this)
    }

    /**
     * 初始化系統語言
     */
    private fun initSystemLanguage(){
        SystemLanguage.values().find { it.apiCode == SystemSP.apiLanguageCode }?.systemLanguage?.let {
            val locale = Locale(it) // 新的語言設定
            Locale.setDefault(locale)
            LanguageUtils.applyLanguage(locale)
        }
    }

}