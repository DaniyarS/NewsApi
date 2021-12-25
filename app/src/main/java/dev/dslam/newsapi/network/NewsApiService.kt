package dev.dslam.newsapi.network

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import dev.dslam.newsapi.models.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getHeadlineNewses(
        @Query("q") q: String ?= null,
        @Query("apiKey") apiKey: String ?= null,
        @Query("pageSize") pageSize: Int ?= null,
        @Query("page") page: Int ?= null
    ): ApiResponse

    @GET("everything")
    suspend fun getEverything(
        @Query("q") q: String ?= null,
        @Query("apiKey") apiKey: String ?= null,
        @Query("pageSize") pageSize: Int ?= null,
        @Query("page") page: Int ?= null
    ): ApiResponse
}