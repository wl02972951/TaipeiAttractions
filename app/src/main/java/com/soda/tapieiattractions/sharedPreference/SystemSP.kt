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
    var systemLanguage :String
        get() {
            return kv.decodeString("systemLanguage","zh-tw")?:"zh-tw"
        }
        set(value) {
            kv.encode("systemLanguage",value)
        }
}