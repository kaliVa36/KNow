package com.android.know.domain.usecase

import com.android.know.domain.repository.NewsRepository

class TopHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend operator fun invoke() = newsRepository.getTopHeadlines()
}
