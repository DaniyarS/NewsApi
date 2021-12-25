package dev.dslam.newsapi.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.dslam.newsapi.local.FavoriteNewsDao
import dev.dslam.newsapi.models.Article
import dev.dslam.newsapi.network.NewsApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val favoriteNewsDao: FavoriteNewsDao
) : NewsRepository {

    override fun retrieveHeadline(
        search: String,
        apiKey: String,
        pageSize: Int,
        page: Int,
        maxPageSize: Int
    ) : Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(pageSize = pageSize, maxSize = maxPageSize),
                pagingSourceFactory = { HeadlinePagingSource(newsApiService, search, apiKey) }).flow
    }

    override fun retrieveAll(
        search: String,
        apiKey: String,
        pageSize: Int,
        page: Int,
        maxPageSize: Int
    ) : Flow<PagingData<Article>> {
        val data =  Pager(config = PagingConfig(pageSize = pageSize, maxSize = maxPageSize),
            pagingSourceFactory = { EverythingPagingSource(newsApiService, search, apiKey) })

        return data.flow
    }

    override fun saveFavoriteNews(article: Article) {
        favoriteNewsDao.insertOnArticle(article)
    }

    override fun getFavoriteNews(): List<Article>? {
        return favoriteNewsDao.getFavorite()
    }
}