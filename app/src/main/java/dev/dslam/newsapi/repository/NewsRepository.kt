package dev.dslam.newsapi.repository

import androidx.lifecycle.MutableLiveData
import dev.dslam.newsapi.models.Article

interface NewsRepository {
    fun retrieveHeadline(
        topHeadlinesList: MutableLiveData<List<Article>>,
        search: String,
        apiKey: String
    )
    fun retrieveAll(allNewsList: MutableLiveData<List<Article>>, search: String, apiKey: String)
    fun saveFavoriteNews(article: Article)
    fun getFavoriteNews(): List<Article>?
}