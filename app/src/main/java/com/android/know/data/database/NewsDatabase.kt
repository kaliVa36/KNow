package com.android.know.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.know.data.dao.NewsDao
import com.android.know.domain.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class NewsDatabase: RoomDatabase() {
    abstract val dao: NewsDao
}
