package com.soda.tapieiattractions.activity.mainActivity.fragment.resultFragment

import android.graphics.Paint
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.soda.tapieiattractions.R
import com.soda.tapieiattractions.activity.mainActivity.MainViewModel
import com.soda.tapieiattractions.activity.webViewActivity.WebViewActivity
import com.soda.tapieiattractions.adapters.tagAdapter.TagAdapter
import com.soda.tapieiattractions.base.BaseVbFragment
import com.soda.tapieiattractions.databinding.FragmentResultBinding
import com.soda.tapieiattractions.model.AttractionData
import com.soda.tapieiattractions.tools.LinkFactory


class ResultFragment : BaseVbFragment<FragmentResultBinding>(FragmentResultBinding::inflate) {

    companion object {
        private const val TRANSITION_DURATION = 300L
    }

    private val viewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class.java] }

    private val args: ResultFragmentArgs by navArgs()
    private val data: AttractionData? by lazy { viewModel.getAttractionData(args.index) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //處理轉場動畫
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move).apply {
                duration = TRANSITION_DURATION
            }
        sharedElementReturnTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move).apply {
                duration = TRANSITION_DURATION
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layoutView = super.onCreateView(inflater, container, savedInstanceState)
        //處理轉場物件
        binding.vpContent.transitionName = "${getString(R.string.transitionCover)}${args.index}"
        binding.tvTitle.transitionName = "${getString(R.string.transitionTitle)}${args.index}"
        binding.includeToolBar.root.transitionName =
            "${getString(R.string.transitionToolbar)}${args.index}"
        return layoutView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initModel()
    }

    private fun initView() {
        Log.d("SODA_DEBUG", "data = $data")
        data?.let { data ->
            with(binding) {
                vpContent.apply {
                    offscreenPageLimit = 3
                    //預載3頁避免滑入才載圖片的空缺
                    adapter = PhotoViewPagerAdapter(data.images.map { it.src })
                }
                if (data.images.isEmpty()) {
                    containerIndicator.visibility = View.INVISIBLE
                }
                indicator.attachToPager(vpContent)
                tvAddress.text = data.address
                tvTitle.text = data.name
                rvTag.apply {
                    layoutManager = FlexboxLayoutManager(requireContext()).apply {
                        flexDirection = FlexDirection.ROW
                        flexWrap = FlexWrap.WRAP
                        justifyContent = JustifyContent.FLEX_START
                        alignItems = AlignItems.FLEX_START
                    }
                    adapter = TagAdapter(data.getAllTag()) {

                    }
                }
                tvDescription.text = data.introduction
                btBack.setOnClickListener {
                    findNavController().popBackStack()
                }
                btShare.setOnClickListener {
                    LinkFactory.startShareIntent(requireContext(),data.name,data.url)
                }
                tvWeb.apply {
                    text = data.official_site
                    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
                    setOnClickListener {
                        WebViewActivity.start(requireContext(), data.official_site)
                    }
                }
                with(includeToolBar) {
                    btFaceBook.setOnClickListener {
                        if (data.facebook.isNotBlank()) {
                            LinkFactory.startFaceBookIntent(requireContext(), data.facebook)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.no_facebook),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                    btPhone.setOnClickListener {
                        if (data.tel.isNotBlank()) {
                            LinkFactory.startPhoneCallIntent(requireContext(), data.tel)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.no_phone),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                    btEmail.setOnClickListener {
                        if (data.email.isNotBlank()) {
                            LinkFactory.startEmailIntent(requireContext(), data.email)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.no_email),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                    btMap.setOnClickListener {
                        LinkFactory.startGoogleMapIntent(
                            requireContext(),
                            data.nlat,
                            data.elong,
                            data.name
                        )
                    }
                }
            }
        }

    }
    private fun initModel() {

    }


}