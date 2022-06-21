package com.demo.list

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.demo.list.databinding.PostListFragmentBinding
import com.demo.list.epoxy.ItemEpoxyController
import com.jatri.common.base.BaseFragment
import com.jatri.entity.ApiResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostListFragment : BaseFragment<PostListFragmentBinding>() {
    override fun viewBindingLayout(): PostListFragmentBinding = PostListFragmentBinding.inflate(layoutInflater)

    private val viewModel by viewModels<PostListViewModel>()
    private lateinit var controller:ItemEpoxyController

    override fun initializeView(savedInstanceState: Bundle?) {
        controller = ItemEpoxyController{
            showToastMessage(it)
        }
        binding.listRv.adapter = controller.adapter
        fetchPostList()
    }

    /**
     * ...fetch post list from remote db
     * ...display list on ui
     */
    private fun fetchPostList(){
        viewModel.fetchPostList().observe(viewLifecycleOwner) { apiResponse ->
            when (apiResponse) {
                is ApiResponse.Success -> {
                    controller.itemList = apiResponse.data.toList()
                    controller.requestModelBuild()
                }
                is ApiResponse.Failure -> showToastMessage(apiResponse.message)
                is ApiResponse.Progress -> showProgressBar(apiResponse.loading, binding.progressBar)
            }
        }
    }
}