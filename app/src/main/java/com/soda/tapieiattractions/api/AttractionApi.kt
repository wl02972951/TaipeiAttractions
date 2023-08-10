package com.soda.tapieiattractions.api

import android.util.Log
import com.google.gson.Gson
import com.soda.tapieiattractions.model.AttractionModel
import com.soda.tapieiattractions.model.CategoryModel
import com.soda.tapieiattractions.tools.UrlBuilder
import io.reactivex.Single

/**
 * 參閱API
 * https://www.travel.taipei/open-api/swagger/ui/index#/Attractions/Attractions_All
 */
object AttractionApi {

    /**
     * 取得畫面資料
     */
    fun getAttraction(page:Int,categoryQuery: String?=null):Single<AttractionModel>{
        val url = UrlBuilder(ApiServiceManager.getBaseUrl()+ "Attractions/All")
            .addQuery("page",page)
            .addQuery("categoryIds",categoryQuery)
            .build()
        return ApiServiceManager.get(url).map {
            val response = it.string()
            return@map Gson().fromJson(response,AttractionModel::class.java)
        }
    }

    /**
     * 取得所有分類
     */
    fun getCategory():Single<CategoryModel>{
        val url = UrlBuilder(ApiServiceManager.getBaseUrl()+ "Miscellaneous/Categories")
            .addQuery("type","Attractions")
            .build()

        return ApiServiceManager.get(url).map {
            val response = it.string()
            return@map Gson().fromJson(response,CategoryModel::class.java)
        }
    }
}