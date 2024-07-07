package com.android.know.data.repository

import com.android.know.data.datasource.NewsDataSource
import com.android.know.data.mapper.toArticleEntity
import com.android.know.domain.entity.ArticleEntity
import com.android.know.domain.repository.NewsRepository

class NewsRepositoryImpl(private val newsDataSource: NewsDataSource) : NewsRepository {
    override var articles: List<ArticleEntity> = listOf()

    override suspend fun getNews(q: String, sortBy: String?, page: Int): Result<List<ArticleEntity>> {
        return newsDataSource.getNews(q = q, sortBy = sortBy, page = page).fold(
            onSuccess = { articleData ->
                articles = articleData.articles?.mapIndexed { index, articleModel ->
                    articleModel.toArticleEntity(index)
                } ?: listOf()
                Result.success(articles)
            },
            onFailure = {
                articles = listOf()
                Result.failure(Throwable(it))
            }
        )
    }

    override suspend fun getTopHeadlines(category: String, page: Int): Result<List<ArticleEntity>> {
        return newsDataSource.getTopHeadlines(category, page).fold(
            onSuccess = { articleData ->
                articles = articleData.articles?.mapIndexed { index, articleModel ->
                    articleModel.toArticleEntity(index)
                } ?: listOf()
                Result.success(articles)
            },
            onFailure = {
                articles = listOf()
                Result.failure(Throwable(it))
            }
        )
    }

    override fun getArticleById(id: String): ArticleEntity? {
        return articles.firstOrNull { it.id == id }
    }
}
