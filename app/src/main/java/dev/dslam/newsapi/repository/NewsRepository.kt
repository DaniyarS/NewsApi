package dev.dslam.newsapi.repository

import androidx.paging.PagingData
import dev.dslam.newsapi.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    //Получение хэдлайн новостей
    fun retrieveHeadline(
        search: String,
        apiKey: String,
        pageSize: Int,
        page: Int,
        maxPageSize: Int
    ): Flow<PagingData<Article>>

    //Получение всех новостей
    fun retrieveAll(
        search: String,
        apiKey: String,
        pageSize: Int,
        page: Int,
        maxPageSize: Int
    ): Flow<PagingData<Article>>

    fun saveFavoriteNews(article: Article)
    fun getFavoriteNews(): List<Article>?
}