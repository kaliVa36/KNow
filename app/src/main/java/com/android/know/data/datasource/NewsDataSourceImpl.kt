package com.android.know.data.datasource

import com.android.know.data.WHITE_SPACE
import com.android.know.data.model.NewsData
import com.android.know.data.requestBody
import com.android.know.data.service.NewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URLEncoder

class NewsDataSourceImpl(private val newsService: NewsService) : NewsDataSource {
    override suspend fun getNews(q: String, sortBy: String?, page: Int): Result<NewsData> {
        val key = if (q.contains(WHITE_SPACE)) {
            withContext(Dispatchers.IO) {
                URLEncoder.encode(q, Charsets.UTF_8.name())
            }
        } else {
            q
        }
        return requestBody(newsService.getNews(q = key, sortBy = sortBy, page = page))
    }

    override suspend fun getTopHeadlines(category: String, page: Int): Result<NewsData> {
        return requestBody(newsService.getTopHeadlines(page = page, category = category,))
    }
}
