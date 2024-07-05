package com.android.know.data.repository

import com.android.know.data.datasource.NewsDataSource
import com.android.know.data.mapper.toArticleEntity
import com.android.know.domain.entity.ArticleEntity
import com.android.know.domain.repository.NewsRepository

class NewsRepositoryImpl(private val newsDataSource: NewsDataSource) : NewsRepository {
    override suspend fun getNews(): Result<List<ArticleEntity>> {
        return newsDataSource.getNews().fold(
            onSuccess = { articleData -> Result.success(articleData.articles?.map { it.toArticleEntity() } ?: listOf()) },
            onFailure = { Result.failure(Throwable(it)) }
        )
    }
}
