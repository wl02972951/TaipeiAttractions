package com.soda.tapieiattractions.activity.landingActivity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.soda.tapieiattractions.R
import com.soda.tapieiattractions.base.BaseVbActivity
import com.soda.tapieiattractions.databinding.ActivityLandingBinding

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
                viewModel.getAttractionData()
            }
        }
    }

    private fun initModel(){
        with(viewModel){
            getAttractionData()
            attractionLiveData.observe(this@LandingActivity){
                binding.tvText.text = it.toString()
            }
        }
    }

}