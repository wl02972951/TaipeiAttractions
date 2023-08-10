package com.soda.tapieiattractions.globalData

import com.google.gson.Gson
import com.soda.tapieiattractions.model.CategoryModel
import com.soda.tapieiattractions.sharedPreference.SystemSP

object CategoryData {

    /**
     * 對外全域資料
     */
    val data :CategoryModel.Category
        get() {
            if (_data == null){
                _data = generateCategoryData()
            }
            return _data!!
        }

    /**
     * 內部資料
     */
    private var _data :CategoryModel.Category? = null

    /**
     * 設定分類資料
     * 同時保存資料到sharedPreference
     */
    fun setCategoryData(data: CategoryModel.Category){
        this._data = data
        SystemSP.categoryData = Gson().toJson(data)
    }

    /**
     * 有時後資料會被GC回收掉
     * 因此若被收時 需要重新取得資料
     * 方法為先取得sharedPreference的資料
     */
    private fun generateCategoryData():CategoryModel.Category{
        Gson().fromJson(SystemSP.categoryData,CategoryModel.Category::class.java).let {
            return it
        }
    }

    /**
     * 將資料扁平化
     * 取得所有分類資料
     */
    fun getAllCategory():List<CategoryModel.Tag>{
        return buildList {
            addAll(data.category)
            addAll(data.friendly)
            addAll(data.services)
            addAll(data.target)
        }
    }
}