package com.android.know.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.know.data.dao.NewsDao
import com.android.know.domain.Pagination
import com.android.know.domain.entity.ArticleEntity
import com.android.know.domain.usecase.TopHeadlinesUseCase
import com.android.know.ui.components.category.ArticleCategories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val topHeadlinesUseCase: TopHeadlinesUseCase,
    private val newsDao: NewsDao,
) : ViewModel() {
    private val _homeScreenData = MutableStateFlow(HomeScreenData())
    val homeScreenData = _homeScreenData.asStateFlow()

    init {
        getTopHeadlines()
        getSavedArticles()
    }

    private fun getTopHeadlines(page: Int = Pagination.FIRST_PAGE) {
        viewModelScope.launch {
            topHeadlinesUseCase(_homeScreenData.value.selectedCategory.name.lowercase(), page = page).fold(
                onSuccess = { articles ->
                    if (articles.size < Pagination.PAGE_SIZE) {
                        _homeScreenData.update { it.copy(isLastPage = true) }
                    }
                    _homeScreenData.update { it.copy(articles = articles, listSize = articles.size) }
                },
                onFailure = {}
            )
        }
    }

    fun setCategory(value: ArticleCategories) {
        if (value != _homeScreenData.value.selectedCategory) {
            _homeScreenData.update { it.copy(selectedCategory = value) }
            getTopHeadlines()
        }
    }

    private fun getSavedArticles() {
        viewModelScope.launch {
            newsDao.getAllRecords().collectLatest { articles ->
                _homeScreenData.update { it.copy(savedArticles = Articles(articles)) }
            }
        }
    }

    fun saveArticle(articleEntity: ArticleEntity) {
        viewModelScope.launch {
            if (!_homeScreenData.value.savedArticles.data.any { it.title == articleEntity.title }) {
                newsDao.upsertDataModel(articleEntity)
                val savedArticles = _homeScreenData.value.savedArticles.data + articleEntity
                _homeScreenData.update { it.copy(savedArticles = Articles(savedArticles)) }
            }
        }
    }

    fun getScrolledPosition(index: Int) {
        if (
            index >= (_homeScreenData.value.listSize * (_homeScreenData.value.pageCounter + Pagination.INDEX_FIX)) -
            Pagination.LAST_INDEX_FIX && !_homeScreenData.value.isLastPage
        ) {
            incrementPage()
        }
    }

    private fun incrementPage() {
        val page = _homeScreenData.value.pageCounter + 1
        getTopHeadlines(page)
        _homeScreenData.update { it.copy(pageCounter = page) }
    }
}