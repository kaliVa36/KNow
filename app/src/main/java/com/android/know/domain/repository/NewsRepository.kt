package com.android.know.domain.repository

import com.android.know.domain.entity.ArticleEntity

interface NewsRepository {
    val articles: List<ArticleEntity>
    suspend fun getNews(q: String, sortBy: String?, page: Int): Result<List<ArticleEntity>>
    suspend fun getTopHeadlines(category: String, page: Int): Result<List<ArticleEntity>>
    fun getArticleById(id: String): ArticleEntity?
}
