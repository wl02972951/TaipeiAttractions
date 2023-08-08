package com.soda.tapieiattractions.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soda.tapieiattractions.R
import com.soda.tapieiattractions.base.BaseVbDialog
import com.soda.tapieiattractions.databinding.DialogChoiceLanguageBinding
import com.soda.tapieiattractions.enumClass.SystemLanguage

class ChoiceLanguageDialog(context: Context,private val onLanguageSelect:(language:SystemLanguage)->Unit):
    BaseVbDialog<DialogChoiceLanguageBinding>(context,DialogChoiceLanguageBinding::inflate){

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            initView()

        }

        private fun initView() {
            with(binding){

            }
        }


    private class LanguageAdapter: RecyclerView.Adapter<LanguageViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
            return LanguageViewHolder.newInstance(parent)
        }

        override fun getItemCount(): Int {
            return SystemLanguage.values().size
        }

        override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
            val systemLanguage = SystemLanguage.values()[position]

        }

    }

    private class LanguageViewHolder(view: View): RecyclerView.ViewHolder(view){
        companion object{
            fun newInstance(parent: ViewGroup): LanguageViewHolder {
                return LanguageViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_system_language, parent, false)
                )
            }

        }

        private val binding = DialogChoiceLanguageBinding.bind(view)
        fun bind(language: SystemLanguage){

        }
    }

}