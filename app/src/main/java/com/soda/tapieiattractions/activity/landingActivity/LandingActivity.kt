package com.soda.tapieiattractions.activity.landingActivity

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.soda.tapieiattractions.activity.mainActivity.MainActivity
import com.soda.tapieiattractions.base.BaseVbActivity
import com.soda.tapieiattractions.databinding.ActivityLandingBinding
import com.soda.tapieiattractions.tools.Logger
import java.lang.IllegalStateException

class LandingActivity : BaseVbActivity<ActivityLandingBinding>(ActivityLandingBinding::inflate) {

    companion object{
        fun restartActivity(context: Context){
            val intent = Intent(context,LandingActivity::class.java).apply{
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

    private val viewModel by lazy { ViewModelProvider(this)[LandingViewModel::class.java] }
    private var isAnimateFinish = false
    private var isLoadFinish = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initModel()
    }

    private fun initView() {
        with(binding) {
            lottieWelcome.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator) {}
                override fun onAnimationCancel(p0: Animator) {}
                override fun onAnimationRepeat(p0: Animator) {}
                override fun onAnimationEnd(p0: Animator) {
                    isAnimateFinish = true
                    goToMain()
                }
            })
        }
    }

    private fun initModel() {
        with(viewModel) {
            startLanding()
            landingProgress.observe(this@LandingActivity) {
                when (it) {
                    LandingViewModel.LandingProgress.FinishLanding -> {
                        isLoadFinish= true
                        goToMain()
                    }
                    is LandingViewModel.LandingProgress.Error -> {
                        handleError(it.errorCode)
                    }

                    else -> {
                        Logger.d("LandingActivity", "landing progress: $it")
                    }
                }
            }
        }
    }

    /**
     * 處理錯誤代碼行為
     */
    private fun handleError(errorCode: Int) {
        when (errorCode) {
            LandingViewModel.LandingErrorCode.GetCategoryError -> {

            }
            else -> {
                throw IllegalStateException("LandingActivity handle error but error code not define: $errorCode")
            }
        }

    }

    /**
     * 前往首頁
     */
    private fun goToMain() {
        if (isLoadFinish && isAnimateFinish) {
            MainActivity.start(this)
            finish()
        }

    }

}