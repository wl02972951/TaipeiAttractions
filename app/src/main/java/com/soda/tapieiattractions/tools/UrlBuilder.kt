package com.soda.tapieiattractions.tools

/**
 * Url 建造者模式
 * 用來建立帶參數的api
 * 並判斷若參數是null或空時則不加入
 */
class UrlBuilder(private val baseUrl:String) {
    private val query = mutableMapOf<String,String>()

    /**
     * 增加參數
     * @param key 參數名稱
     * @param value 參數值
     */
    fun addQuery(key:String,value:Any?): UrlBuilder {

        value?.let {//只有value不為null時才加入
            if (it.toString().isNotBlank()){ //轉為string 時不為空
                query[key] = it.toString() //使用kt的toString()方法轉型
            }
        }
        return this
    }

    /**
     * 建立Url
     */
    fun build():String{
        var url = baseUrl
        var isFirst = true
        query.forEach{
            // 第一個參數前面加上? 其他參數前面加上&
            url += if (isFirst) "?" else "&"
            url += "${it.key}=${it.value}"
            isFirst = false
        }
        return url
    }
}