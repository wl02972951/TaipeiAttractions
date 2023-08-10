package com.soda.tapieiattractions.api

import android.util.Log
import com.soda.tapieiattractions.sharedPreference.SystemSP
import com.soda.tapieiattractions.tools.Logger
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiServiceManager {

    private const val BASE_URL = "https://www.travel.taipei/open-api/"

    private val rtf by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())//轉換為Gson
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //轉換為rxJava
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    private var okHttpClient = OkHttpClient()
        .newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val api = rtf.create(ApiService::class.java)

    /**
     * 取得BaseUrl
     * 並設定語言
     */
    fun getBaseUrl ():String{
        return "$BASE_URL${SystemSP.apiLanguageCode}/"
    }


    fun get(url:String):Single<ResponseBody?>{
        return api.get(url, createBaseHeader())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnError {
                Logger.e("DEBUG_API","call api $url error: ${it.message}")
                Logger.e("DEBUG_API",Log.getStackTraceString(it))
            }
    }

    /**
     * 建立基本的header
     *
     * @return Map<String,String>
     */
    private fun createBaseHeader():Map<String,String>{
        return mapOf(
            "Accept" to "application/json"
        )
    }

}