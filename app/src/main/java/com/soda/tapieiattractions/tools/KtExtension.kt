package com.soda.tapieiattractions.tools

import android.content.res.Resources
import android.util.TypedValue

class KtExtension {

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



}