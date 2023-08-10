package com.soda.tapieiattractions.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.soda.tapieiattractions.R
import com.soda.tapieiattractions.base.BaseVbDialog
import com.soda.tapieiattractions.databinding.DialogChoiceLanguageBinding
import com.soda.tapieiattractions.databinding.ItemSystemLanguageBinding
import com.soda.tapieiattractions.enumClass.SystemLanguage
import com.soda.tapieiattractions.tools.loadImage

class ChoiceLanguageDialog(context: Context,private val onLanguageSelect:(language:SystemLanguage)->Unit):
    BaseVbDialog<DialogChoiceLanguageBinding>(context,DialogChoiceLanguageBinding::inflate){

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            initView()

        }

        private fun initView() {
            with(binding){
                rvContent.apply {
                    adapter = LanguageAdapter{
                        dismiss()
                        onLanguageSelect(it)
                    }
                    layoutManager = LinearLayoutManager(context)
                }
                btCancel.setOnClickListener {
                    dismiss()
                }

            }
        }


    private class LanguageAdapter(private val onLanguageSelect: (language: SystemLanguage) -> Unit):
        RecyclerView.Adapter<LanguageViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
            return LanguageViewHolder.newInstance(parent, onLanguageSelect = onLanguageSelect)
        }

        override fun getItemCount(): Int {
            return SystemLanguage.values().size
        }

        override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
            val systemLanguage = SystemLanguage.values()[position]
            holder.bind(systemLanguage)
        }

    }

    private class LanguageViewHolder(private val binding:ItemSystemLanguageBinding,private val onLanguageSelect: (language: SystemLanguage) -> Unit): RecyclerView.ViewHolder(binding.root){
        companion object{
            fun newInstance(parent: ViewGroup,onLanguageSelect: (language: SystemLanguage) -> Unit): LanguageViewHolder {
                return LanguageViewHolder(
                    ItemSystemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    onLanguageSelect
                )
            }

        }
        fun bind(language: SystemLanguage){
            binding.tvLanguage.text = language.language
            binding.ivFlag.loadImage(language.flagIcon)
            itemView.setOnClickListener {
                onLanguageSelect(language)
            }
        }
    }

}