package com.android.know.domain.repository

import com.android.know.domain.entity.ArticleEntity

interface NewsRepository {
    suspend fun getNews(): Result<List<ArticleEntity>>
    suspend fun getTopHeadlines(category: String): Result<List<ArticleEntity>>
}
