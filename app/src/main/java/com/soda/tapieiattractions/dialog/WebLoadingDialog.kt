package com.soda.tapieiattractions.dialog

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import com.soda.tapieiattractions.base.BaseVbDialog
import com.soda.tapieiattractions.databinding.DialogWebViewLoadingBinding

class WebLoadingDialog(context: Context,private val onBackPress:()->Unit) :BaseVbDialog<DialogWebViewLoadingBinding>(context,DialogWebViewLoadingBinding::inflate){

    companion object{
        //避免過快載入 造成閃屏 使用體驗不好
        //因此最短過場時間為1.5秒
        private const val EXPECT_LOADING_TIME = 1500L
    }
    private var mDummyProcess = 0
    private var animator:ValueAnimator? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(true)
        initView()
    }

    private fun initView() {
        with(binding){
            vBack.setOnClickListener {
                onBackPress()
                dismiss()
            }
        }
    }
    fun progressChange (progress : Int){
        if (progress<=mDummyProcess){
            return
        }
        if (animator?.isRunning==true){
            animator?.cancel()
        }
        animator = ValueAnimator.ofInt(mDummyProcess,progress).apply {
            duration = (progress-mDummyProcess)* EXPECT_LOADING_TIME /100
            addUpdateListener {
                mDummyProcess = animatedValue as Int
                binding.tvProgress.text = animatedValue.toString()
                if (mDummyProcess>=100){
                    dismiss()
                }
            }
        }
        animator?.start()
    }
}