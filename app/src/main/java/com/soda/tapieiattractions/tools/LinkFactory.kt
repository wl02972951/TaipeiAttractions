package com.soda.tapieiattractions.tools

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

/**
 * 用於解析網址的工具類
 */
object LinkFactory {

    /**
     *
     */
    fun parseLinkIntent(context: Context, url:String?){
        if (url.isNullOrBlank()) return
        val trimUrl = url.trim()
        try {
            val uri = Uri.parse(trimUrl)
            when(uri.scheme){
                "http","https"->{
                    startWebIntent(context,trimUrl)
                }
            }
        }catch (e:Exception){
            Logger.e("DEBUG_LINK","can't open link : $url ,error:${e.message}")
        }
    }



    private fun startWebIntent(context: Context,url:String){
        try {
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(url)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        }catch (e:Exception){
            Log.e("DEBUG_LINK","can't open link : $url ,error:${e.message}")
        }
    }
}