package com.soda.tapieiattractions.dialog

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.soda.tapieiattractions.R
import com.soda.tapieiattractions.adapters.tagAdapter.SelectableTagAdapter
import com.soda.tapieiattractions.base.BaseVbDialog
import com.soda.tapieiattractions.databinding.DialogFilterBinding
import com.soda.tapieiattractions.globalData.CategoryData

/**
 * 可多選的tag dialog
 */
class FilterDialog (context: Context,private val onConform:(String)->Unit):
    BaseVbDialog<DialogFilterBinding>(context,DialogFilterBinding::inflate, R.style.dialog_right_drawer){

    private val mCateAdapter by lazy { SelectableTagAdapter(CategoryData.data.category.map { it.name }) }
    private val mSerAdapter by lazy { SelectableTagAdapter(CategoryData.data.services.map { it.name }) }
    private val mFriendAdapter by lazy { SelectableTagAdapter(CategoryData.data.friendly.map { it.name }) }
    private val mTarAdapter by lazy { SelectableTagAdapter(CategoryData.data.target.map { it.name }) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding){
            rvCategory.apply {
                layoutManager = getLayoutManger()
                adapter = mCateAdapter
            }
            rvService.apply {
                layoutManager = getLayoutManger()
                adapter = mSerAdapter
            }
            rvFriendly.apply {
                layoutManager = getLayoutManger()
                adapter = mFriendAdapter
            }
            rvTarget.apply {
                layoutManager = getLayoutManger()
                adapter = mTarAdapter
            }
            vCloseView.setOnClickListener {
                dismiss()
            }
            btYes.setOnClickListener {
                onConform(getAllSelectTag())
                dismiss()
            }
        }
    }

    private fun getLayoutManger() = FlexboxLayoutManager(context).apply {
        flexDirection = FlexDirection.ROW
        flexWrap = FlexWrap.WRAP
        justifyContent = JustifyContent.FLEX_START
        alignItems = AlignItems.FLEX_START
    }

    private fun getAllSelectTag():String{
        val list = mutableListOf<String>()
        list.addAll(mCateAdapter.getSelectList())
        list.addAll(mSerAdapter.getSelectList())
        list.addAll(mFriendAdapter.getSelectList())
        list.addAll(mTarAdapter.getSelectList())
        return list.joinToString(separator = ",")

    }


}