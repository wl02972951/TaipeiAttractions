package com.soda.tapieiattractions.activity.landingActivity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.soda.tapieiattractions.R
import com.soda.tapieiattractions.activity.webViewActivity.WebViewActivity
import com.soda.tapieiattractions.base.BaseVbActivity
import com.soda.tapieiattractions.databinding.ActivityLandingBinding
import com.soda.tapieiattractions.dialog.WebLoadingDialog

class LandingActivity : BaseVbActivity<ActivityLandingBinding>(ActivityLandingBinding::inflate) {


    private val viewModel by lazy { ViewModelProvider(this)[LandingViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initModel()
    }

    private fun initView(){
        with(binding){
            tvText.setOnClickListener {
                Log.d("SODA_DEBUG","getData")
                viewModel.getCateGoryData()
            }
        }
    }

    private fun initModel(){
        with(viewModel){
            getCateGoryData()
            attractionLiveData.observe(this@LandingActivity){
                binding.tvText.text = it.toString()
                WebViewActivity.start(this@LandingActivity,"https://www.travel.taipei/zh-tw/attraction/details/19")
            }
        }
    }

}