package com.soda.tapieiattractions.activity.mainActivity.fragment.mainListFragement.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.soda.tapieiattractions.R
import com.soda.tapieiattractions.activity.mainActivity.MainViewModel
import com.soda.tapieiattractions.adapters.tagAdapter.TagAdapter
import com.soda.tapieiattractions.databinding.ItemMainAttractionBinding
import com.soda.tapieiattractions.model.AttractionData
import com.soda.tapieiattractions.tools.loadImage

class MainAttractionVH(private val binding: ItemMainAttractionBinding,private val viewModel: MainViewModel) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup, viewModel: MainViewModel): MainAttractionVH {
            return MainAttractionVH(
                ItemMainAttractionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                viewModel
            )
        }
    }

    private lateinit var transitionCover: String
    private lateinit var transitionTitle: String
    private lateinit var transitionTool: String
    fun bind(attractionData: AttractionData?, pos: Int) {
        transitionCover = "${itemView.context.getString(R.string.transitionCover)}$pos"
        transitionTitle = "${itemView.context.getString(R.string.transitionTitle)}$pos"
        transitionTool = "${itemView.context.getString(R.string.transitionToolbar)}$pos"

        with(binding) {
            attractionData?.let {
                ivCover.loadImage(it.images.firstOrNull()?.src,R.drawable.img_placeholder)
                ivCover.transitionName = transitionCover
                tvTitle.transitionName = transitionTitle
                vTransitionTool.transitionName = transitionTool
                tvTitle.text = it.name
                tvContent.text = it.introduction.replace(Regex("[\\n\\t\\s]+"), "")
                rvTags.apply {
                    layoutManager = FlexboxLayoutManager(itemView.context).apply {
                        flexDirection = FlexDirection.ROW
                        flexWrap = FlexWrap.WRAP
                        justifyContent = JustifyContent.FLEX_START
                        alignItems = AlignItems.FLEX_START
                    }
                    adapter = TagAdapter(it.getAllTag()){
                        viewModel.search(it)
                    }
                }
                itemView.setOnClickListener {
                    val bundle = bundleOf("index" to pos)
                    val trans = FragmentNavigatorExtras(
                        ivCover to transitionCover,
                        vTransitionTool to transitionTool,
                        tvTitle to transitionTitle
                    )
                    Navigation.findNavController(it)
                        .navigate(R.id.action_to_result_page, bundle, null, trans)
                }
            }
        }
    }





}