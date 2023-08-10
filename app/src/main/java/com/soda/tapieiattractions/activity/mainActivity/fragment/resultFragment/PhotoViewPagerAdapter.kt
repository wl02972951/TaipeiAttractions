package com.soda.tapieiattractions.activity.mainActivity.fragment.resultFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.soda.tapieiattractions.R
import com.soda.tapieiattractions.tools.loadImage

class PhotoViewPagerAdapter (private val imageUrls: List<String>) : RecyclerView.Adapter<PhotoViewPagerAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_photo_banner, parent, false)
        return PhotoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(imageUrls.getOrNull(position))
    }

    override fun getItemCount(): Int {
        return if (imageUrls.isEmpty()) 1 else imageUrls.size
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPhoto = itemView.findViewById<ImageView>(R.id.ivPhoto)
        fun bind(imageUrl: String?) {
            ivPhoto.loadImage(imageUrl,R.drawable.img_placeholder)
        }
    }
}