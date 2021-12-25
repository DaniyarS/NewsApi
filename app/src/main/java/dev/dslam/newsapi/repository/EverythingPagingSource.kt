package dev.dslam.newsapi.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.dslam.newsapi.Constants
import dev.dslam.newsapi.models.Article
import dev.dslam.newsapi.network.NewsApiService
import java.lang.Exception
import javax.inject.Inject

class EverythingPagingSource @Inject constructor(
    private val newsApiService: NewsApiService,
    private val search: String,
    private val apiKey: String
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val pageNumber = params.key ?: Constants.FIRST_PAGE_INDEX
            val response = newsApiService.getEverything(
                q = search,
                apiKey = apiKey,
                pageSize = 15,
                page = pageNumber
            )

            LoadResult.Page(
                data = response.articles,
                prevKey = null,
                nextKey = pageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}