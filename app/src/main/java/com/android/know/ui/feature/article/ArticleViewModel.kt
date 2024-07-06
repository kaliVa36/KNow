package com.android.know.ui.feature.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.know.domain.entity.ArticleEntity
import com.android.know.domain.usecase.ArticleByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticleViewModel(private val articleByIdUseCase: ArticleByIdUseCase): ViewModel() {
    private val _articleState = MutableStateFlow(ArticleEntity())
    val articleState = _articleState.asStateFlow()

    fun getArticle(id: String) {
        if (id.isNotBlank()) {
            viewModelScope.launch {
                val article = articleByIdUseCase(id)
                article?.let { _articleState.emit(it) }
            }
        }
    }
}