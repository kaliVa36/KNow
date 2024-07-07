package com.android.know.ui.feature.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.know.data.dao.NewsDao
import com.android.know.domain.entity.ArticleEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedArticlesViewModel(private val newsDao: NewsDao): ViewModel() {
    private val _articlesState = MutableStateFlow(listOf<ArticleEntity>())
    val articlesState = _articlesState.asStateFlow()

    init {
        viewModelScope.launch {
            newsDao.getAllRecords().collectLatest { articles ->
                _articlesState.update { articles }
            }
        }
    }
}