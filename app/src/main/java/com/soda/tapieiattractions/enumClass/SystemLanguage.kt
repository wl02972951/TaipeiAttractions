package com.soda.tapieiattractions.enumClass

import androidx.annotation.DrawableRes
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
 */
enum class SystemLanguage(val apiCode:String,val language:String,@DrawableRes val flagIcon:Int){
    TraditionalChinese("zh-tw","繁體中文" ,R.drawable.icon_taiwan),
    SimplifiedChinese("zh-cn","简体中文" ,R.drawable.icon_china),
    English("en","English",R.drawable.icon_usa),
    Japanese("ja","日本語",R.drawable.icon_japan),
    Korean("ko","한국어",R.drawable.icon_korea),
    Spanish("es","Español",R.drawable.icon_spain),
    Indonesian("id","Indonesia",R.drawable.icon_indonesia),
    Thai("th","แบบไทย",R.drawable.icon_thailand),
    Vietnamese("vi","Tiếng Việt",R.drawable.icon_vietnam)
}