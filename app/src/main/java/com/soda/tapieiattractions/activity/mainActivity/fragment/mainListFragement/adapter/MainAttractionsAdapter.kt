package com.soda.tapieiattractions.activity.mainActivity.fragment.mainListFragement.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.soda.tapieiattractions.activity.mainActivity.MainViewModel
import com.soda.tapieiattractions.model.AttractionData

class MainAttractionsAdapter(private val viewModel: MainViewModel) : ListAdapter<AttractionData, MainAttractionVH>(DataDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAttractionVH {
        return MainAttractionVH.newInstance(parent,viewModel)
    }

    override fun onBindViewHolder(holder: MainAttractionVH, position: Int) {
        val attractionData = getItem(position)
        holder.bind(attractionData,position)
    }

    private class DataDiffUtil: DiffUtil.ItemCallback<AttractionData>(){
        override fun areItemsTheSame(oldItem: AttractionData, newItem: AttractionData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AttractionData, newItem: AttractionData): Boolean {
            return oldItem == newItem
        }
    }
}