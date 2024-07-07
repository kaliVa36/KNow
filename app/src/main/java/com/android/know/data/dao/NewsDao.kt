package com.android.know.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.android.know.domain.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Upsert
    suspend fun upsertDataModel(articleEntity: ArticleEntity)

    @Query("SELECT * FROM articleEntity")
    fun getAllRecords(): Flow<List<ArticleEntity>>
}