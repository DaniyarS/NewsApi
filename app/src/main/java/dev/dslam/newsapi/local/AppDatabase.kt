package dev.dslam.newsapi.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.dslam.newsapi.models.Article
import dev.dslam.newsapi.models.SourceTypeConverters

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(SourceTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getFavoritesDao(): FavoriteNewsDao

    companion object {
        private var dbInstance: AppDatabase? = null

        fun getAppDB(context: Context): AppDatabase {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "daniyarsnews.db"
                )
                    .allowMainThreadQueries()
                    .build()
            }

            return dbInstance!!
        }
    }
}