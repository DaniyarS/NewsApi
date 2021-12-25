package dev.dslam.newsapi.diModules

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dslam.newsapi.Constants
import dev.dslam.newsapi.local.AppDatabase
import dev.dslam.newsapi.local.FavoriteNewsDao
import dev.dslam.newsapi.network.NewsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providedNewsService(retrofit: Retrofit) : NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Singleton
    @Provides
    fun getAppDB(context: Application) : AppDatabase {
        return AppDatabase.getAppDB(context)
    }

    @Singleton
    @Provides
    fun getDao(appDB: AppDatabase): FavoriteNewsDao {
        return appDB.getFavoritesDao()
    }
}