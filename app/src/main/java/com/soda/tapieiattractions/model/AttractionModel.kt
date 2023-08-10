package com.soda.tapieiattractions.model

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import java.io.File
import java.io.Serializable

data class AttractionModel(
    val `data`: List<AttractionData>,
    val total: Int //資料總數
)
/**
 * data exp:
 *  {
 *       "id": 1810,
 *       "name": "龍山文創基地",
 *       "name_zh": null,
 *       "open_status": 1,
 *       "introduction": "體驗式學習行文創基地進而認識艋舺。",
 *       "open_time": "週二至週日 11:00 - 21:00　\r\n週一 休館日\r\n*遇國定假日或連續假日延後一日休館",
 *       "zipcode": "108",
 *       "distric": "萬華區",
 *       "address": "108 臺北市萬華區西園路一段145號B2 龍山寺地下街B2(龍山寺捷運站1號出口)",
 *       "tel": "+886-2-23021598",
 *       "fax": "+886-2-23021599",
 *       "email": "monga.longshan.b2@gmail.com",
 *       "months": "01,07,02,08,03,09,04,10,05,11,06,12",
 *       "nlat": 25.03607,
 *       "elong": 121.49989,
 *       "official_site": "https://longshan-b2.taipei/home/zh-tw",
 *       "facebook": "https://www.facebook.com/mongaB2/",
 *       "ticket": "",
 *       "remind": "",
 *       "staytime": "",
 *       "modified": "2023-08-06 05:02:20 +08:00",
 *       "url": "https://www.travel.taipei/zh-tw/attraction/details/1810",
 *       "category": [
 *         {
 *           "id": 15,
 *           "name": "藝文館所"
 *         }
 *       ],
 *       "target": [],
 *       "service": [],
 *       "friendly": [],
 *       "images": [
 *         {
 *           "src": "https://www.travel.taipei/image/176830",
 *           "subject": "",
 *           "ext": ".jpg"
 *         }
 *       ],
 *       "files": [],
 *       "links": []
 *     }
 *
 *
 * 景點資料
 * 有在使用的資料----------------
 * @param id id //可用於收藏
 * @param address 地址 //用於導覽
 * @param images 圖片 //用於展示
 * @param category 分類 //用於點擊搜尋分類
 * @param introduction 介紹
 *
 * @param elong 東經  可用於google map
 * @param nlat 北緯   可用於google map
 *
 * @param email 電子信箱 //用於社群媒體
 * @param facebook 臉書 //用於社群媒體
 * @param tel 電話      //用於社群媒體
 * @param official_site 官網 //用於社群媒體
 * @param url 官網 //專案需求

 */
data class AttractionData(
    val address: String,
    val name: String,
    val category: List<Category>,
    val introduction: String,
    val elong: Double,
    val nlat: Double,
    val url: String,
    val email: String,
    val facebook: String,
    val images: List<Image>,
    val friendly: List<Category>,
    val service: List<Category>,
    val target: List<Category>,
    val id: Int,
    val official_site: String,
    val tel: String,
    //以下資料在本次專案無用--------------
//    val files: List<File>,
//    val fax: String,
//    val distric: String,
//    val links: List<File>,
//    val modified: String,
//    val months: String,
//    val name_zh: String,
//    val open_status: Int,
//    val open_time: String,
//    val remind: String,
//    val staytime: String,
//    val ticket: String,
//    val zipcode: String
)
{

    /**
     * 取得所有標籤
     */
    fun getAllTag():List<String>{
        return buildList {
            addAll(category.map { it.name })
            addAll(service.map { it.name })
            addAll(friendly.map { it.name })
            addAll(target.map { it.name })
        }
    }
    data class Category(
        val id: Int,
        val name: String
    )

    data class Image(
        val ext: String?,
        val src: String,
        val subject: String
    )


}

