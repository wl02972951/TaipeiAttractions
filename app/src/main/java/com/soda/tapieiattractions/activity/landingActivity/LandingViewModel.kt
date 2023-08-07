package com.soda.tapieiattractions.activity.landingActivity

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soda.tapieiattractions.api.AttractionApi
import com.soda.tapieiattractions.base.BaseViewModel
import com.soda.tapieiattractions.model.AttractionData

class LandingViewModel (application: Application): BaseViewModel(application){


    private val _mAttractionLiveData = MutableLiveData<AttractionData>()
    val attractionLiveData :LiveData<AttractionData>
        get()= _mAttractionLiveData

    fun getSystemLanguage(){

    }


    fun getAttractionData(){
        AttractionApi.getAttraction(1).subscribe({
            _mAttractionLiveData.postValue(it)
        },{
            Log.e(TAG,"getAttractionData error :",it)
        }).autoDispose()


    }


}