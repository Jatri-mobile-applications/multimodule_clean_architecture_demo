package com.example.data.wrapper

import com.jatri.entity.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

abstract class NetworkBoundResources {
    suspend fun <T> downloadNetworkData(callApi: suspend () -> Response<T>): ApiResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = callApi()
                if (response.isSuccessful)
                    ApiResponse.Success(response.body()!!)
                else {
                    val errorResponse = "Something went wrong"
                    ApiResponse.Failure(errorResponse, response.code())
                }
            } catch (e: Exception) {
                ApiResponse.Failure(message(e), code(e))
            }
        }
    }

    private fun message(throwable: Throwable?):String{
        when (throwable) {
            is SocketTimeoutException -> return "Whoops! connection time out, try again!"
            is IOException -> return "No internet connection, try again!"
            is HttpException -> return try {
                "Something went wrong"
            }catch (e:Exception){
                "Unknown error occur, please try again!"
            }
        }
        return "Unknown error occur, please try again!"
    }

    private fun code(throwable: Throwable?):Int{
        return if (throwable is HttpException) (throwable).code()
        else  1000
    }
}