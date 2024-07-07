package com.android.know.ui.feature.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.know.data.dao.NewsDao
import com.android.know.domain.entity.ArticleEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedArticleViewModel(
    private val newsDao: NewsDao
) : ViewModel() {
    private val _articleState = MutableStateFlow(ArticleEntity())
    val articleState = _articleState.asStateFlow()

    fun getArticleById(id: Int) {
        if (id != 0) {
            viewModelScope.launch {
                newsDao.getArticle(id).collectLatest { article ->
                    _articleState.update { article }
                }
            }
        }
    }
}