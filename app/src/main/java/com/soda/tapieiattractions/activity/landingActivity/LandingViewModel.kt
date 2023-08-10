package com.soda.tapieiattractions.activity.landingActivity

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.annotation.IntDef
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soda.tapieiattractions.api.AttractionApi
import com.soda.tapieiattractions.base.BaseViewModel
import com.soda.tapieiattractions.enumClass.SystemLanguage
import com.soda.tapieiattractions.globalData.CategoryData
import com.soda.tapieiattractions.sharedPreference.SystemSP
import java.util.Locale

class LandingViewModel (application: Application): BaseViewModel(application){

    /**
     * 錯誤代碼定義
     */
    @IntDef(LandingErrorCode.GetCategoryError)
    @Retention(AnnotationRetention.SOURCE)
    annotation class LandingErrorCode{
        companion object{
            //取得分類資料錯誤
            const val GetCategoryError = -0x12
        }
    }
    /**
     * Landing進度定義
     */
    sealed class LandingProgress{
        //取得語系
        object GetLanguage: LandingProgress()
        //取得分類
        object GetCategory: LandingProgress()
        //完成
        object FinishLanding: LandingProgress()
        data class Error(@LandingErrorCode val errorCode: Int): LandingProgress()
    }

    private val _landingProgress = MutableLiveData<LandingProgress>()
    val landingProgress :LiveData<LandingProgress> = _landingProgress


    fun startLanding(){
        getSystemDefaultLanguage()
        getCategoryData()
    }


    /**
     * 取得系統預設語言決定Api要使用的語系
     */
    private fun getSystemDefaultLanguage(){
        //如果尚未設定
        if (SystemSP.apiLanguageCode == null){
            _landingProgress.value = LandingProgress.GetLanguage
            //取得當前手機語言
            val language = app.resources.configuration.locales[0].toLanguageTag()
            SystemLanguage.values().forEach { //取手機預設語系
                if (it.systemLanguage == language){
                    SystemSP.apiLanguageCode = it.apiCode
                    return
                }
            }
            //如果都沒有符合的使用英文
            SystemSP.apiLanguageCode = SystemLanguage.English.apiCode
        }else{
            //系統設定語系
            SystemLanguage.values().find { it.apiCode == SystemSP.apiLanguageCode }?.systemLanguage?.let {
                val resources = app.resources
                val configuration = resources.configuration
                configuration.setLocale(Locale(it))
                resources.updateConfiguration(configuration, resources.displayMetrics)
            }

        }

    }
    private fun getCategoryData(){
        _landingProgress.value = LandingProgress.GetCategory
        AttractionApi.getCategory().subscribe({
            CategoryData.setCategoryData(it.data)
            _landingProgress.value = LandingProgress.FinishLanding
        },{
            _landingProgress.value = LandingProgress.Error(LandingErrorCode.GetCategoryError)
            Log.e(TAG,"getCategoryData error : ${it.message}",it)
        }).autoDispose()
    }

}