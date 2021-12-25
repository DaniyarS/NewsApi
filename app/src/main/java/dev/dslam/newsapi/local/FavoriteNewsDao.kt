package dev.dslam.newsapi.local

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.dslam.newsapi.models.ApiResponse
import dev.dslam.newsapi.models.Article

@Dao
interface FavoriteNewsDao {
    @Query("SELECT * FROM articles")
    fun getFavorite(): PagingSource<Int, Article>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOnArticle(article: Article)
}