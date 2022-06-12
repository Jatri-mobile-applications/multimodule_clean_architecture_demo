package com.demo.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.PostListApiUseCase
import com.jatri.entity.ApiResponse
import com.jatri.entity.PostItemApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val postListApiUseCase: PostListApiUseCase
): ViewModel() {
    private var _postItems = MutableLiveData<List<PostItemApiEntity>>()
    val postItems : LiveData<List<PostItemApiEntity>> get() = _postItems

    private var _loadingState = MutableLiveData<Boolean>()
    val loadingState : LiveData<Boolean> get() = _loadingState

    init {
        fetchPostList()
    }

    private fun fetchPostList() {
        Timber.e("api calling start")
        viewModelScope.launch {
            _loadingState.value = true
            when(val data = postListApiUseCase.executeNew())
            {
                is ApiResponse.Success->
                {
                    Timber.e("call success")
                    _postItems.value = data.data.toList()
                }
                is ApiResponse.Failure-> {
                    Timber.e("call failed")
                    _postItems.value = listOf()
                }
                else -> _postItems.value = listOf()
            }

            _loadingState.value = false
        }
    }
}