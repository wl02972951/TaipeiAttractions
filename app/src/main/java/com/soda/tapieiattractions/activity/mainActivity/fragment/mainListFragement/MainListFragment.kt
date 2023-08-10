package com.soda.tapieiattractions.activity.mainActivity.fragment.mainListFragement

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.LanguageUtils
import com.soda.tapieiattractions.activity.landingActivity.LandingActivity
import com.soda.tapieiattractions.activity.mainActivity.MainViewModel
import com.soda.tapieiattractions.activity.mainActivity.fragment.mainListFragement.adapter.MainAttractionsAdapter
import com.soda.tapieiattractions.base.BaseVbFragment
import com.soda.tapieiattractions.databinding.FragmentMainListBinding
import com.soda.tapieiattractions.dialog.ChoiceLanguageDialog
import com.soda.tapieiattractions.enumClass.SystemLanguage
import com.soda.tapieiattractions.sharedPreference.SystemSP
import com.soda.tapieiattractions.tools.hide
import com.soda.tapieiattractions.tools.show
import java.util.Locale


class MainListFragment : BaseVbFragment<FragmentMainListBinding>(FragmentMainListBinding::inflate) {

    private val viewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class.java] }
    private val mAdapter by lazy { MainAttractionsAdapter(viewModel) }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initModel()
    }

    private fun initView() {
        with(binding){
            rvContent.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = mAdapter
                //下滑加載更多
                setOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener(){
                    override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (recyclerView.layoutManager!=null){
                            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                            if (!viewModel.isLoading && viewModel.page!=null){
                                if (layoutManager.findLastVisibleItemPosition() >= layoutManager.itemCount-1){
                                    viewModel.loadAttractionsData()
                                }
                            }
                        }
                    }
                })
            }
            //刷新資料
            swipe.setOnRefreshListener {
                viewModel.loadAttractionsData(true)
            }
            btLanguage.setOnClickListener {
                ChoiceLanguageDialog(requireContext()){
                    switchSystemLanguage(it)
                }.show()
            }

            etSearch. setOnEditorActionListener { textView, actionId, keyEvent ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_SEND,
                    EditorInfo.IME_ACTION_DONE,
                    EditorInfo.IME_ACTION_GO,
                    EditorInfo.IME_ACTION_SEARCH ->{
                        val text = textView.text.toString()
                        etSearch.clearFocus()
                        viewModel.search(text)
                        KeyboardUtils.hideSoftInput(requireActivity())
                        true
                    }
                    else-> false
                }
            }
        }
    }

    /**
     * 切換語言
     */
    private fun switchSystemLanguage(it: SystemLanguage) {
        val local =  Locale(it.systemLanguage)
        SystemSP.apiLanguageCode = it.apiCode
        LanguageUtils.applyLanguage(local)
        LandingActivity.restartActivity(requireActivity())
        requireActivity().finish()
    }

    private fun initModel() {
        with(viewModel){
            attractionsData.observe(viewLifecycleOwner){
                mAdapter.submitList(it)
                if (it.isEmpty()){
                    binding.containerNoData.show()
                    mAdapter.submitList(it)
                }else{
                    binding.swipe.isRefreshing = false
                    binding.rvContent.show()
                    binding.containerNoData.hide()
                    mAdapter.submitList(it){
                        binding.shimmerView.apply{
                            stopShimmer()
                            hideShimmer()
                            visibility = View.INVISIBLE
                        }
                    }
                }
            }
            searchQuery.observe(viewLifecycleOwner){
                binding.etSearch.setText(it)
            }
        }
    }
}