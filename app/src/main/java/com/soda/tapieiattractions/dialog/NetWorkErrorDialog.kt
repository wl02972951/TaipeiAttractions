package com.soda.tapieiattractions.dialog

import android.content.Context
import android.os.Bundle
import com.soda.tapieiattractions.base.BaseVbDialog
import com.soda.tapieiattractions.databinding.DialogNetWorkErrorBinding

class NetWorkErrorDialog(context: Context) :
    BaseVbDialog<DialogNetWorkErrorBinding>(context, DialogNetWorkErrorBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding) {

        }
    }
}