package com.soda.tapieiattractions.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.soda.tapieiattractions.R

/**
 * Base ViewBinding dialog
 * 封裝ViewBinding的Dialog
 *
 * 並使用 showRecordMap 來避免重複跳出POP窗
 *
 */
abstract class BaseVbDialog<B : ViewBinding>(
    context: Context,
    private val bindingFactory: (LayoutInflater) -> B,
    style: Int = R.style.dialog_fullScreen,
) : Dialog(context, style) {
    companion object{
        //點擊間格
        private const val ReShowCDTime = 500L
        //短時間內連續點擊不會show出新的
        //秀出POP窗的MAP String=>Dialog class名稱 Long => 上次秀出時間
        private val showRecordMap = mutableMapOf<String,Long>()
    }
    private val className by lazy { javaClass.name }

    protected val binding: B by lazy { bindingFactory(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window?.apply {
            //這邊改寫dialog layout param 使用mach_parent
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    /**
     * 顯示Dialog
     * @param ignoreCheck 是否忽略重複顯示檢查(連續跳出)
     */
    fun show(ignoreCheck:Boolean = false){
        if (ignoreCheck){ //忽略則直接顯示
            super.show()
        }else{ //其餘使用override的show
            this.show()
        }
    }
    override fun show() {
        val currentTime = System.currentTimeMillis()
        val lastShowTime = showRecordMap[className] ?:0L
        if (currentTime>= lastShowTime+ ReShowCDTime){
            showRecordMap[className] = currentTime
            super.show()
        }
    }

    override fun dismiss() {
        //關閉時移除map中的元件
        showRecordMap.remove(className)
        super.dismiss()
    }

}