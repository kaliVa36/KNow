package com.android.know.data.datasource

import com.android.know.data.model.NewsData
import com.android.know.data.service.NewsService
import java.lang.IllegalArgumentException

class NewsDataSourceImpl(private val newsService: NewsService): NewsDataSource {
    override suspend fun getNews(): Result<NewsData> {
        val request = newsService.getNews()
        return try {
            if (request.isSuccessful) {
                request.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Throwable(request.message()))
            } else {
                val message = request.errorBody()?.string()
                Result.failure(Throwable(message))
            }
        } catch (ex: IllegalArgumentException) {
            Result.failure(ex)
        }
    }
}
