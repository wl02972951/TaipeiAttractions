package com.soda.tapieiattractions.api

import android.util.Log
import com.google.gson.Gson
import com.soda.tapieiattractions.model.AttractionData
import com.soda.tapieiattractions.model.CategoryData
import com.soda.tapieiattractions.tools.UrlBuilder
import io.reactivex.Single

object AttractionApi {

    /**
     * 取得畫面資料
     */
    fun getAttraction(page:Int,category: List<Int>? = null):Single<AttractionData>{
        val url = UrlBuilder(ApiServiceManager.getBaseUrl()+ "Attractions/All")
            .apply {
                addQuery("page",page)
                if (category.isNullOrEmpty().not()){ //如果分類不是空的
                    val categoryQuery = category?.joinToString(separator = ",") //將list 轉成 以,分隔的字串
                    addQuery("category",categoryQuery) //加入分類參數
                }
            }
            .build()
        Log.d("SODA_DEBUG","url = $url")
        return ApiServiceManager.get(url).map {
            val response = it.string()
            Log.d("SODA_DEBUG","response = $response")
            return@map Gson().fromJson(response,AttractionData::class.java)
        }
    }

    /**
     * 取得所有分類
     */
    fun getCategory():Single<CategoryData>{
        val url = UrlBuilder(ApiServiceManager.getBaseUrl()+ "Miscellaneous/Categories")
            .addQuery("type","Attractions")
            .build()

        return ApiServiceManager.get(url).map {
            val response = it.string()
            Log.d("SODA_DEBUG","response = $response")
            return@map Gson().fromJson(response,CategoryData::class.java)
        }
    }
}