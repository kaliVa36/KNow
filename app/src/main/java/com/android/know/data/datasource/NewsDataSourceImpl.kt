package com.android.know.data.datasource

import com.android.know.data.model.NewsData
import com.android.know.data.requestBody
import com.android.know.data.service.NewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URLEncoder

class NewsDataSourceImpl(private val newsService: NewsService): NewsDataSource {
    override suspend fun getNews(): Result<NewsData> {
        return requestBody(newsService.getNews())
    }

    override suspend fun getTopHeadlines(): Result<NewsData> {
        return requestBody(newsService.getTopHeadlines())
    }
}
