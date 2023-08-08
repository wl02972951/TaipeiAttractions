package com.soda.tapieiattractions.activity.landingActivity

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build.VERSION_CODES.P
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soda.tapieiattractions.api.AttractionApi
import com.soda.tapieiattractions.base.BaseViewModel
import com.soda.tapieiattractions.model.AttractionData

class LandingViewModel (application: Application): BaseViewModel(application){


    private val _mAttractionLiveData = MutableLiveData<String>()
    val attractionLiveData :LiveData<String>
        get()= _mAttractionLiveData

    fun startLanding(){

    }

    fun checkNetworkAvailable():Boolean{
        // 獲取 ConnectivityManager，來查詢網絡連接狀態
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // 獲取當前活躍的網絡，如果沒有活躍的網絡，則返回 false
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        //如果拿到的是空資料代表目前沒有可以用的網路
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return true
    }


    fun getAttractionData(){
        AttractionApi.getAttraction(1).subscribe({
        },{
            Log.e(TAG,"getAttractionData error :",it)
        }).autoDispose()


    }

    fun getCateGoryData(){
        AttractionApi.getCategory().subscribe({
            _mAttractionLiveData.postValue(it.toString())
        },{

        }).autoDispose()
    }


}