package com.android.know.domain.usecase

import com.android.know.domain.repository.NewsRepository

class ArticleByIdUseCase(private val newsRepository: NewsRepository) {
    operator fun invoke(id: String) = newsRepository.getArticleById(id)
}
