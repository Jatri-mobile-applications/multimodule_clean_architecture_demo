package com.demo.list

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.demo.list.databinding.PostListFragmentBinding
import com.jatri.common.base.BaseFragment
import com.jatri.common.extfun.setUpVerticalRecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostListFragment : BaseFragment<PostListFragmentBinding>() {
    override fun viewBindingLayout(): PostListFragmentBinding  =  PostListFragmentBinding.inflate(layoutInflater)

    private val viewModel by viewModels<PostListViewModel>()
    private val adapter = PostListAdapter()

    override fun initializeView(savedInstanceState: Bundle?) {
        requireActivity().setUpVerticalRecyclerView(binding.listRv,adapter)
        observer()
    }

    /**
     * ...fetch post list from remote db
     * ...display list on ui
     */
    private fun observer(){
        viewModel.postItems.observe(viewLifecycleOwner) { apiResponse ->
            if(apiResponse.isNotEmpty())
                adapter.submitList(apiResponse)
            else showToastMessage("No data found")
        }

        viewModel.loadingState.observe(viewLifecycleOwner){
            showProgressBar(it, binding.progressBar)
        }
    }
}