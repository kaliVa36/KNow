package com.android.know.domain.usecase

import com.android.know.domain.Pagination
import com.android.know.domain.repository.NewsRepository

class NewsUseCase(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(q: String, sortBy: String? = null, page: Int = Pagination.FIRST_PAGE) =
        newsRepository.getNews(q, sortBy, page)
}
