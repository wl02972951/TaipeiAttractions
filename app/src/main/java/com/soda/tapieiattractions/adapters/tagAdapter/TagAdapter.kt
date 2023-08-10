package com.soda.tapieiattractions.adapters.tagAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.soda.tapieiattractions.R

/**
 * 標籤adapter
 */
class TagAdapter(private val tagList: List<String>,private val onTagClick:(String)->Unit={}) :
    RecyclerView.Adapter<TagAdapter.TagVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_attraction_tag, parent, false)
        return TagVH(view,onTagClick)
    }

    override fun getItemCount(): Int {
        return tagList.size
    }

    override fun onBindViewHolder(holder: TagVH, position: Int) {
        holder.bind(tagList[position])
    }

    class TagVH(view: View,val onTagClick: (String) -> Unit) : RecyclerView.ViewHolder(view) {
        private val tvTag = itemView.findViewById<TextView>(R.id.tvTag)
        fun bind(tag: String) {
            tvTag.text = tag
            itemView.setOnClickListener {
                onTagClick(tag)
            }
        }
    }

}