package com.android.know.ui.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.know.domain.Pagination
import com.android.know.domain.usecase.NewsUseCase
import com.android.know.ui.components.sorting.ArticleSorting
import com.android.know.ui.components.sorting.DATE_SORTING_KEY_WORD
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    private val _searchUIState = MutableStateFlow(SearchData())
    val searchUIState = _searchUIState.asStateFlow()

    fun onValueChange(value: String) {
        _searchUIState.update { it.copy(searchValue = value) }
    }

    fun onSearch() {
        getNews()
    }

    private fun getNews(page: Int = Pagination.FIRST_PAGE_NEWS) {
        val searchKey = _searchUIState.value.searchValue
        val sortingKey =
            if (_searchUIState.value.selectedSorting == ArticleSorting.DATE) {
                DATE_SORTING_KEY_WORD
            } else {
                _searchUIState.value.selectedSorting?.name?.lowercase()
            }
        if (searchKey.isNotBlank()) {
            viewModelScope.launch {
                newsUseCase(q = searchKey, sortBy = sortingKey, page = page).fold(
                    onSuccess = { articles ->
                        if (articles.size < Pagination.PAGE_SIZE) {
                            _searchUIState.update { it.copy(isLastPage = true) }
                        }
                        _searchUIState.update { it.copy(articles = articles, listSize = articles.size) }
                    },
                    onFailure = {}
                )
            }
        }
    }

    fun onSortClick(sort: ArticleSorting) {
        if (_searchUIState.value.selectedSorting != sort && _searchUIState.value.articles.isNotEmpty()) {
            _searchUIState.update { it.copy(selectedSorting = sort) }
            onSearch()
        }
    }

    fun onRemove() {
        _searchUIState.update { it.copy(selectedSorting = null) }
    }

    fun getScrolledPosition(index: Int) {
        if (
            index >= (_searchUIState.value.listSize * (_searchUIState.value.pageCounter)) -
            Pagination.LAST_INDEX_FIX && ! _searchUIState.value.isLastPage
        ) {
            incrementPage()
        }
    }

    private fun incrementPage() {
        val page = _searchUIState.value.pageCounter + 1
        getNews(page)
        _searchUIState.update { it.copy(pageCounter = page) }
    }
}