package com.soda.tapieiattractions.enumClass

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.soda.tapieiattractions.R

/**
 * Api語言種類
 * Doc: https://www.travel.taipei/open-api/swagger/ui/index#/Events
 *
 * zh-tw -正體中文
 * zh-cn -簡體中文
 * en -英文
 * ja -日文
 * ko -韓文
 * es -西班牙文
 * id -印尼文
 * th -泰文
 * vi -越南文
 *
 * @param systemLanguage Android系統語言代碼
 * @param apiCode Api 代碼
 * @param language 顯示名稱
 * @param flagIcon 國旗icon
 *
 * P.S 目前印度的API 有問題，所以暫時不開放
 */
enum class SystemLanguage(val systemLanguage: String,val apiCode:String,val language:String,@DrawableRes val flagIcon:Int){
    TraditionalChinese("zh-TW","zh-tw","繁體中文" ,R.drawable.icon_taiwan),
    SimplifiedChinese("zh-CN","zh-cn","简体中文" ,R.drawable.icon_china),
    English("en","en","English",R.drawable.icon_usa),
    Japanese("ja","ja","日本語",R.drawable.icon_japan),
    Korean("ko","ko","한국어",R.drawable.icon_korea),
    Spanish("es","es","Español",R.drawable.icon_spain),
    Indonesian("in-ID","id","Indonesia",R.drawable.icon_indonesia),
    Thai("th","th","แบบไทย",R.drawable.icon_thailand),
    Vietnamese("vi","vi","Tiếng Việt",R.drawable.icon_vietnam)
}