package dev.dslam.newsapi.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dslam.newsapi.models.Article
import dev.dslam.newsapi.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class EverythingFragmentViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private var everythingList: MutableLiveData<List<Article>> = MutableLiveData()

    fun getEverythingObserver(): MutableLiveData<List<Article>> {
        return everythingList
    }

    fun loadEverything(search: String, apiKey: String) {
        repository.retrieveAll(everythingList, search, apiKey)
    }

    fun saveFavoriteNews(article: Article) {
        repository.saveFavoriteNews(article)
    }
}