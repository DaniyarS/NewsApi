package dev.dslam.newsapi.diModules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.dslam.newsapi.repository.NewsRepository
import dev.dslam.newsapi.repository.NewsRepositoryImpl

@InstallIn(ViewModelComponent::class)
@Module
abstract class NewsModule {
    @Binds
    abstract fun getNewsSource(repo: NewsRepositoryImpl) : NewsRepository
}