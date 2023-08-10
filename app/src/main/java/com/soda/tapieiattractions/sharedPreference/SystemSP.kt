package com.soda.tapieiattractions.sharedPreference

import com.tencent.mmkv.MMKV

/**
 * 系統SharedPreference
 * 使用MMKV
 * GitHub Doc: [https://github.com/Tencent/MMKV/wiki/home_cn]
 */
object SystemSP {

    private val kv  by lazy { MMKV.defaultMMKV() }

    /**
     * 系統語言
     * 預設為繁體中文
     */
    var apiLanguageCode :String?
        get() {
            return kv.decodeString("systemLanguage",null)
        }
        set(value) {
            kv.encode("systemLanguage",value)
        }

    /**
     * 儲存所有分類資料
     */
    var categoryData:String
        get() {
            return kv.decodeString("categoryData","")?:""
        }
        set(value) {
            kv.encode("categoryData",value)
        }
}