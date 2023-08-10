package com.soda.tapieiattractions.tools

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

/**
 * 各種外開intent行為
 */
object IntentUtil {

    /**
     * 開啟google map
     * @param nlat  北緯
     * @param elong 東經
     * @param locationName 位置名稱
     *
     * @return 是否有google map app 可以啟動
     * intent 的使用參考 https://developers.google.com/maps/documentation/urls/android-intents?hl=zh-tw
     */

    fun startGoogleMapIntent(
        context: Context,
        nlat: Double,
        elong: Double,
        locationName: String
    ): Boolean {
        val uri = Uri.parse("geo:$nlat,$elong?q=$locationName")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        return try {
            context.startActivity(mapIntent)
            true
        } catch (e: Exception) {
            Log.e("DEBUG_LINK", "can't open google map ,error:${e.message}")
            false
        }
    }

    /**
     * 開啟分享intent
     */
    fun startShareIntent(context: Context, title: String, url: String) {
        val shareText = "$title \n $url"
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }
        try {
            context.startActivity(shareIntent)
        }catch (e:Exception){
            Log.e("DEBUG_LINK", "can't open share ,error:${e.message}")
        }
    }

    /**
     * 開啟faceBook Intent
     */
    fun startFaceBookIntent(context: Context,url:String){
        val uri = Uri.parse("fb://facewebmodal/f?href=$url")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            startWebIntent(context,url)
            Log.e("DEBUG_LINK", "can't open facebook ,error:${e.message}")
        }
    }

    /**
     * 開啟打電話intent
     */
    fun startPhoneCallIntent(context: Context, phone: String) {
        val uri = Uri.parse("tel:$phone")
        val intent = Intent(Intent.ACTION_DIAL, uri)
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e("DEBUG_LINK", "can't open phone call ,error:${e.message}")
        }

    }

    /**
     * 開啟email intent
     */
    fun startEmailIntent(context: Context, email: String) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
            }
            context.startActivity(intent)
        }catch (e:Exception){
            Log.e("DEBUG_EMAIL","open email intent failed ${e.message}")
        }

    }


    /**
     * 開啟網頁intent
     */
    private fun startWebIntent(context: Context, url: String) {
        try {
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(url)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e("DEBUG_LINK", "can't open link : $url ,error:${e.message}")
        }
    }
}