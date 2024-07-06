package com.android.know.data.service

import com.android.know.data.Default
import com.android.know.data.NEWS
import com.android.know.data.QueryParameters
import com.android.know.data.TOP_HEADLINES
import com.android.know.data.model.NewsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Mocked data
interface NewsService {
    @GET(NEWS)
    suspend fun getNews(
        @Query(QueryParameters.Q) q: String? = null,
        @Query(QueryParameters.FROM) from: String? = null,
        @Query(QueryParameters.SORT_BY) sortBy: String? = null,
        @Query(QueryParameters.DOMAIN) domain: String? = null,
        @Query(QueryParameters.TO) to: String? = null,
        @Query(QueryParameters.PAGE) page: Int = 1
    ): Response<NewsData>

    @GET(TOP_HEADLINES)
    suspend fun getTopHeadlines(
        @Query(QueryParameters.COUNTRY) country: String? = Default.DEFAULT_COUNTRY,
        @Query(QueryParameters.CATEGORY) category: String? = null,
        @Query(QueryParameters.PAGE) page: Int = 1,
    ): Response<NewsData>
}
