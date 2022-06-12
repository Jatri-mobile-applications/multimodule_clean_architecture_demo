package com.example.data.repoimpl

import androidx.lifecycle.LiveData
import com.example.data.mapper.PostApiMapper
import com.example.data.mapper.mapFromApiResponse
import com.example.data.wrapper.NetworkBoundResources
import com.example.domain.repository.PostApiRepository
import com.jatri.api.service.PostApiService
import com.jatri.entity.ApiResponse
import com.jatri.entity.PostItemApiEntity
import javax.inject.Inject

class PostApiRepoImpl @Inject constructor(
    private val postApiService: PostApiService,
    private val postApiMapper: PostApiMapper
):NetworkBoundResources,PostApiRepository{

//    override fun fetchPostList(): LiveData<ApiResponse<List<PostItemApiEntity>>> {
//        return  object : NetworkBoundResource<List<PostItemApiResponse>>(){
//            override fun createCall(): Single<List<PostItemApiResponse>> = postApiService.fetchPostList()
//        }.asLiveData().map {data->
//            mapFromApiResponse(data,postApiMapper)
//        }
//    }

    override suspend fun fetchPostList(): ApiResponse<List<PostItemApiEntity>> {
        return mapFromApiResponse(downloadNetworkData {
            postApiService.fetchPostListData()
        }, postApiMapper)
    }

    override fun fetchSinglePost(id: Int): LiveData<ApiResponse<PostItemApiEntity>> {
        TODO("Not yet implemented")
    }
}