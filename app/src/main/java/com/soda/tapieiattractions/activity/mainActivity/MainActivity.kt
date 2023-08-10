package com.soda.tapieiattractions.activity.mainActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.soda.tapieiattractions.base.BaseVbActivity
import com.soda.tapieiattractions.databinding.ActivityMainBinding

class MainActivity : BaseVbActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    companion object{
        fun start(context: Context){
            val intent = Intent(context,MainActivity::class.java).apply{
                //刪除所有的Activity，並且將此Activity置於最頂層
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            context.startActivity(intent)
        }
    }


    /**
     * 實作點擊空白處收起鍵盤
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        try {
            ev?.let {
                if (it.action == MotionEvent.ACTION_DOWN) {
                    //點擊空白處將鍵盤收起
                    val v = currentFocus
                    if (isShouldHideKeyboard(v, it)) {
                        val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        im.hideSoftInputFromWindow(v?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                        v?.clearFocus()
                    }

                }
            }
            return super.dispatchTouchEvent(ev)
        }catch (e: Exception){
            return false
        }
    }

    /**
     * 偵測點擊位置判斷是否要關閉鍵盤
     */
    private fun isShouldHideKeyboard(view: View?, event: MotionEvent): Boolean {
        if (view != null && view is EditText) {  //判断得到的焦点控件是否包含EditText
            val l = intArrayOf(0, 0)
            view.getLocationInWindow(l)
            val left = l[0]
            //得到输入框在屏幕中上下左右的位置
            val top = l[1]
            val bottom = top + view.getHeight()
            val right = left + view.getWidth()
            return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
        }
        // 如果焦点不是EditText则忽略
        return false
    }

}