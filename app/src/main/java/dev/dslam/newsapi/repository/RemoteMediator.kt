package dev.dslam.newsapi.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.RoomDatabase
import dev.dslam.newsapi.local.FavoriteNewsDao
import dev.dslam.newsapi.models.Article
import dev.dslam.newsapi.network.NewsApiService

/*
@ExperimentalPagingApi
class RemoteMediator(
    private val query: String,
    private val apiKey: String,
    private val pageSize: Int,
    private val page: Int,
    private val favoriteNewsDao: FavoriteNewsDao,
    private val newsApiService: NewsApiService
) : RemoteMediator<Int, Article>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()

                    if (lastItem == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    lastItem.id
                }
            }

            val response = newsApiService.getHeadlineNewses(
                q = query,
                apiKey = apiKey,
                page = page,
                pageSize = pageSize
            )

            database.
        }
    }
}*/