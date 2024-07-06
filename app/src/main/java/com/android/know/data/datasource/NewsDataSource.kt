package com.android.know.data.datasource

import com.android.know.data.model.NewsData

interface NewsDataSource {
    suspend fun getNews(): Result<NewsData>
    suspend fun getTopHeadlines(category: String): Result<NewsData>
}
