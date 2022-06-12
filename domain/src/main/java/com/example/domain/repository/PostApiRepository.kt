package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.jatri.entity.ApiResponse
import com.jatri.entity.PostItemApiEntity

interface PostApiRepository {
    suspend fun fetchPostList():ApiResponse<List<PostItemApiEntity>>
    fun fetchSinglePost(id:Int):LiveData<ApiResponse<PostItemApiEntity>>
}