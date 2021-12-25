package dev.dslam.newsapi.repository

import androidx.lifecycle.MutableLiveData
import dev.dslam.newsapi.local.FavoriteNewsDao
import dev.dslam.newsapi.models.ApiResponse
import dev.dslam.newsapi.models.Article
import dev.dslam.newsapi.network.NewsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val favoriteNewsDao: FavoriteNewsDao
) : NewsRepository {

    override fun retrieveHeadline(
        topHeadlinesList: MutableLiveData<List<Article>>,
        search: String,
        apiKey: String
    ) {
        val call: Call<ApiResponse> =
            newsApiService.getHeadlineNewses(topic = search, apiKey = apiKey)
        call.enqueue(object : Callback<ApiResponse> {

            //Сперва пытаемся получить данные из сети, если не получилось показываем лайкнутые
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.articles?.let { topHeadlinesList.postValue(it) }
                } else {
                    if (getFavoriteNews() != null) {
                        topHeadlinesList.postValue(getFavoriteNews())
                    } else {
                        topHeadlinesList.postValue(null)
                    }
                }
            }

            //Показываем лайкнутые новости
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                if (getFavoriteNews() != null) {
                    topHeadlinesList.postValue(getFavoriteNews())
                } else {
                    topHeadlinesList.postValue(null)
                }
            }

        })
    }

    override fun retrieveAll(
        allNewsList: MutableLiveData<List<Article>>,
        search: String,
        apiKey: String
    ) {
        val call: Call<ApiResponse> = newsApiService.getEverything(topic = search, apiKey = apiKey)
        call.enqueue(object : Callback<ApiResponse> {
            //Сперва пытаемся получить данные из сети, если не получилось показываем лайкнутые
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.articles?.let { allNewsList.postValue(it) }
                } else {
                    if (getFavoriteNews() != null) {
                        allNewsList.postValue(getFavoriteNews())
                    } else {
                        allNewsList.postValue(null)
                    }
                }
            }

            //Показываем лайкнутые новости
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                if (getFavoriteNews() != null) {
                    allNewsList.postValue(getFavoriteNews())
                } else {
                    allNewsList.postValue(null)
                }
            }

        })
    }

    override fun saveFavoriteNews(article: Article) {
        favoriteNewsDao.insertOnArticle(article)
    }

    override fun getFavoriteNews(): List<Article>? {
        return favoriteNewsDao.getFavorite()
    }

}