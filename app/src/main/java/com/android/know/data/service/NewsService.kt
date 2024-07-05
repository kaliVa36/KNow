package com.android.know.data.service

import com.android.know.data.NEWS
import com.android.know.data.QueryParameters
import com.android.know.data.model.NewsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Mocked data
interface NewsService {
    @GET(NEWS)
    suspend fun getNews(
        @Query(QueryParameters.Q) q: String = "trump",
        @Query(QueryParameters.FROM) from: String? = null,
        @Query(QueryParameters.SORT_BY) sortBy: String? = null,
    ): Response<NewsData>
}
