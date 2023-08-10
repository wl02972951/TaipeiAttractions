package com.soda.tapieiattractions.adapters.tagAdapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hjq.shape.view.ShapeTextView
import com.soda.tapieiattractions.R

/**
 * 可多選的Tag Adapter
 */
class SelectableTagAdapter (private val tagList: List<String>) : RecyclerView.Adapter<SelectableTagAdapter.TagVH>() {
    //當前選取的tag
    private var mSelectList = mutableSetOf<String>()
    fun getSelectList():Set<String>{
        return mSelectList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_select_tag, parent, false)
        return TagVH(view){name, isSelect ->
            if (isSelect) {
                mSelectList.add(name)
            }else{
                mSelectList.remove(name)
            }
        }
    }

    override fun getItemCount(): Int {
        return tagList.size
    }

    override fun onBindViewHolder(holder: TagVH, position: Int) {
        holder.bind(tagList[position])
    }

    /**
     * @param onTagSelect (name,isSelect) 點擊標籤的callBack name:標籤名稱 isSelect:是否選取
     */
    class TagVH(view: View,val onTagSelect: (name:String,isSelect:Boolean) -> Unit) : RecyclerView.ViewHolder(view) {
        private val tvTag = itemView.findViewById<ShapeTextView>(R.id.tvTag)
        private var isSelected = false
        fun bind(tag: String) {
            tvTag.text = tag
            setSelect()
            itemView.setOnClickListener {
                isSelected = !isSelected
                onTagSelect(tag,isSelected)
                setSelect()
            }
        }
        private fun setSelect(){
            if (isSelected){
                tvTag.shapeDrawableBuilder.apply {
                    solidColor = Color.parseColor("#ed702d")
                }.buildBackgroundDrawable()
                tvTag.setTextColor(Color.WHITE)
            }else{
                tvTag.shapeDrawableBuilder.apply {
                    solidColor = Color.TRANSPARENT
                }.buildBackgroundDrawable()
                tvTag.setTextColor(Color.BLACK)
            }
        }
    }
}