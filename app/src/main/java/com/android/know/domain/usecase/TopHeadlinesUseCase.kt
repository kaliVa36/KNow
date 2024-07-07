package com.android.know.domain.usecase

import com.android.know.domain.Pagination
import com.android.know.domain.repository.NewsRepository

class TopHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(category: String, page: Int = Pagination.FIRST_PAGE) = newsRepository.getTopHeadlines(category, page)
}
