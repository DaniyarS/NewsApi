package dev.dslam.newsapi.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dslam.newsapi.models.Article
import dev.dslam.newsapi.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class HeadlinesFragmentViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    private var headLines: MutableLiveData<List<Article>> = MutableLiveData()

    fun getHeadlines(): MutableLiveData<List<Article>> {
        return headLines
    }

    fun loadHeadlines(search: String, apiKey: String) {
        repository.retrieveHeadline(headLines, search, apiKey)
    }

    fun saveFavoriteNews(article: Article) {
        repository.saveFavoriteNews(article)
    }
}