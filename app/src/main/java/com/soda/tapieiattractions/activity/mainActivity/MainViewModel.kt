package com.soda.tapieiattractions.activity.mainActivity

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soda.tapieiattractions.api.AttractionApi
import com.soda.tapieiattractions.base.BaseViewModel
import com.soda.tapieiattractions.globalData.CategoryData
import com.soda.tapieiattractions.model.AttractionData
import com.soda.tapieiattractions.model.AttractionModel

class MainViewModel(application: Application) :BaseViewModel(application) {

    //篩選資料
    private var filterSidList = mutableListOf<Int>()

    //搜尋關鍵字
    private val _searchQuery :MutableLiveData<String> = MutableLiveData("")
    val searchQuery: LiveData<String>
        get() = _searchQuery

    //景點資料
    private val _attractionsData = MutableLiveData<List<AttractionData>>()
    val attractionsData: LiveData<List<AttractionData>>
        get() = _attractionsData

    //Api 分頁資料
    var page :Int? = 1
    //是否加載中
    var isLoading = false

    init {
        if (attractionsData.value.isNullOrEmpty()){
            loadAttractionsData()
        }
    }

    /**
     * 加載更多景點資料
     */
    fun loadAttractionsData(isRefresh:Boolean = false){
        //當為NUll 或 正在加載時 直接return
        if (isRefresh){
            page=1
            isLoading = false
        }
        if (page == null || isLoading){ //避免重複加載
            return
        }else{
            val loadPage = page?:0
            AttractionApi.getAttraction(loadPage,getCategoryIdQuery()).subscribe({ attraction->
                isLoading = false
                if (attraction.data.isEmpty()){ //當資料為空時代表加載結束 沒有更多資料了
                    page = null
                }else{
                    //如果是刷新的則重新載入
                    _attractionsData.postValue(if (isRefresh){
                        attraction.data
                    }else{
                        (_attractionsData.value?: listOf()).plus(attraction.data)
                    })
                    page = loadPage +1
                }
            },{
                _attractionsData.postValue(emptyList())
                page = null
                isLoading = false
                Log.e(TAG, "loadAttractionsData error: ", it)
            }).autoDispose()
        }

    }

    /**
     * 拿取景點資料
     * @param position 指定位置
     */
    fun getAttractionData(position:Int):AttractionData?{
        return _attractionsData.value?.getOrNull(position)
    }


    /**
     * 取得分類篩選資料
     * 將List 轉成 用,分隔的字串
     * 例： list(1,2,3) -> "1,2,3"
     */
    private fun getCategoryIdQuery():String?{
        return if (filterSidList.isEmpty()){
            null
        }else{
            filterSidList.joinToString(separator = ",")
        }
    }

    /**
     * 搜尋標籤
     */
    fun search(query:String){
        _searchQuery.value = query
        //如果搜尋是空的 則直接載入全部資料
        if (query.isBlank()){
            filterSidList = mutableListOf()
            loadAttractionsData(true)
            return
        }
        //先分類資料
        //將資料分割成陣列
        val searchQuery = query.split("[，、,\\s]+".toRegex())

        //如果分類資料中有包含任何搜尋字元
        //則加入搜尋陣列
        val filterData = CategoryData.getAllCategory().filter {
            searchQuery.any { cell->
                it.name.contains(cell)
            }
        }.map { it.id }

        //如果沒有任何可用tag 則直接回傳沒有搜尋結果
        if (filterData.isEmpty()){
            filterSidList.clear()
            _attractionsData.value = listOf()
            page = null
            return
        }else{ //如果有則加入搜尋條件
            filterSidList = filterData.toMutableList()
            loadAttractionsData(true)
        }
    }



}