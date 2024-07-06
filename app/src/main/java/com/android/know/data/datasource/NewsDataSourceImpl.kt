package com.android.know.data.datasource

import com.android.know.data.model.NewsData
import com.android.know.data.requestBody
import com.android.know.data.service.NewsService

class NewsDataSourceImpl(private val newsService: NewsService): NewsDataSource {
    override suspend fun getNews(): Result<NewsData> {
        return requestBody(newsService.getNews())
    }

    override suspend fun getTopHeadlines(category: String): Result<NewsData> {
        return requestBody(newsService.getTopHeadlines(category = category))
    }
}
