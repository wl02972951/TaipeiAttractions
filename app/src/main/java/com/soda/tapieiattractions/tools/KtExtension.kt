package com.soda.tapieiattractions.tools

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


/**
 * 將PX 轉換成 DP
 * @return Int : DP
 */
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    ).toInt()





/**
 * 讀取本地圖片
 * 會優先檢查View的Context是否為可用狀態
 */
fun ImageView?.loadImage(@DrawableRes imgRes: Int, @DrawableRes placeHolderRes: Int? = null) {
    if (this != null ) {
        if (placeHolderRes != null) {
            Glide.with(this).load(imgRes).placeholder(placeHolderRes)
                .error(placeHolderRes).into(this)
        } else {
            Glide.with(this).load(imgRes).into(this)
        }
    }
}

/**
 * 讀取網路圖片
 * 會優先檢查View的Context是否為可用狀態
 */
fun ImageView?.loadImage(imgUrl: String?, @DrawableRes placeHolderRes: Int? = null) {
    if (this != null) {
        when {
            !imgUrl.isNullOrBlank() && placeHolderRes != null -> { //圖片與預覽圖皆不為空
                Glide.with(this)
                    .load(imgUrl)
                    .placeholder(placeHolderRes)
                    .error(placeHolderRes)
                    .into(this)
            }
            !imgUrl.isNullOrBlank() && placeHolderRes == null -> { //圖片不為空 預覽圖為空
                Glide.with(this).load(imgUrl).into(this)
            }
            imgUrl.isNullOrBlank() && placeHolderRes != null -> {// 圖片為空 預覽圖不為空
                Glide.with(this).load(placeHolderRes).into(this)
            }
        }
    }
}

/**
 * View 擴展
 * show() -> Visible
 * hide()-> INVISIBLE
 * remove()->GONE
 */
fun View?.show(){
    this?.visibility = View.VISIBLE
}

fun View?.hide() {
    this?.visibility = View.INVISIBLE
}

fun View?.remove(){
    this?.visibility = View.GONE
}

