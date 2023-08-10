package com.soda.tapieiattractions.activity.webViewActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.window.OnBackInvokedDispatcher
import com.soda.tapieiattractions.base.BaseVbActivity
import com.soda.tapieiattractions.databinding.ActivityWebViewBinding
import com.soda.tapieiattractions.dialog.WebLoadingDialog

class WebViewActivity : BaseVbActivity<ActivityWebViewBinding>(ActivityWebViewBinding::inflate) {
    companion object{
        fun start(context: Context,webUrl:String){
            val intent = Intent(context,WebViewActivity::class.java).apply{
                putExtra("webUrl",webUrl)
            }
            context.startActivity(intent)
        }
    }

    private val webUrl by lazy { intent.getStringExtra("webUrl")?:"" }
    private lateinit var mWebLoadingDialog: WebLoadingDialog
    //是否已經show loading
    private var isLoadingShow = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLoadingDialog()
        initView()
    }

    /**
     * 初始化載入中Dialog
     */
    private fun initLoadingDialog(){
        mWebLoadingDialog = WebLoadingDialog(this) {
            onBackPressedDispatcher.onBackPressed()
        }.apply {
            //當畫面被取消時 結束activiy
            setOnCancelListener {
                finish()
            }
            isLoadingShow = true
            show()
            progressChange(10)
        }
    }

    private fun initView() {
        with(binding.webView){
            settings.apply {
                javaScriptEnabled = true
            }
            webChromeClient = object :WebChromeClient(){
                //網頁載入進度
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    val isActivityAlive = isFinishing.not() && isDestroyed.not()
                    if (isActivityAlive){ //確保activity 是否已經被銷毀 避免Exception
                        if (!mWebLoadingDialog.isShowing && !isLoadingShow) {
                            //有時候同時執行dialog 會還沒跑出來
                            isLoadingShow = true
                            mWebLoadingDialog.show()
                        }
                        mWebLoadingDialog.progressChange(newProgress)
                    }
                }
            }
            loadUrl(webUrl)
        }
    }

    override fun onDestroy() {
        //停止載入釋放資源 避免記憶體浪費
        with(binding.webView)  {
            stopLoading()
            removeAllViews()
            destroy()
        }
        super.onDestroy()
    }

}