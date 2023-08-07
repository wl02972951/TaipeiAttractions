package com.soda.tapieiattractions.api

import android.util.Log
import com.google.gson.Gson
import com.soda.tapieiattractions.model.AttractionData
import com.soda.tapieiattractions.tools.UrlBuilder
import io.reactivex.Single

object AttractionApi {

    /**
     * 取得畫面資料
     */
    fun getAttraction(page:Int):Single<AttractionData>{
        val url = UrlBuilder(ApiServiceManager.getBaseUrl()+ "Attractions/All")
            .addQuery("page",page)
            .build()
        Log.d("SODA_DEBUG","url = $url")
        return ApiServiceManager.get(url).map {
            val response = it.string()
            Log.d("SODA_DEBUG","response = $response")
            return@map Gson().fromJson(response,AttractionData::class.java)
        }
    }
}