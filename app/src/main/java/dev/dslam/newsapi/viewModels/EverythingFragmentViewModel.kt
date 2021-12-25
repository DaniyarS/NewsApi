package dev.dslam.newsapi.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dslam.newsapi.Constants
import dev.dslam.newsapi.models.Article
import dev.dslam.newsapi.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class EverythingFragmentViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    fun loadEverything(search: String, apiKey: String) : Flow<PagingData<Article>> {
        return repository.retrieveAll(
            search,
            apiKey,
            pageSize = Constants.PAGE_SIZE,
            page = Constants.FIRST_PAGE_INDEX,
            maxPageSize = Constants.MAX_PAGE_SIZE
        ).cachedIn(viewModelScope)
    }

    fun saveFavoriteNews(article: Article) {
        repository.saveFavoriteNews(article)
    }
}