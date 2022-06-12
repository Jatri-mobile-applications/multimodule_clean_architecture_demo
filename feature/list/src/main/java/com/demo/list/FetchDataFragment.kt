package com.demo.list

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.demo.list.databinding.FragmentMainBinding
import com.jatri.common.base.BaseFragment
import com.jatri.common.extfun.clickWithDebounce
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FetchDataFragment : BaseFragment<FragmentMainBinding>() {
    override fun viewBindingLayout(): FragmentMainBinding =  FragmentMainBinding.inflate(layoutInflater)

    private val viewModel by viewModels<PostListViewModel>()
    private val adapter = PostListAdapter()

    override fun initializeView(savedInstanceState: Bundle?) {
        binding.fetchDataTv.clickWithDebounce {
            findNavController().navigate(FetchDataFragmentDirections.actionFetchDataFragmentToPostListFragment())
        }
    }
}