package com.android.know.ui.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        val searchKey = _searchUIState.value.searchValue
        val sortingKey =
            if (_searchUIState.value.selectedSorting == ArticleSorting.DATE) {
                DATE_SORTING_KEY_WORD
            } else {
                _searchUIState.value.selectedSorting?.name?.lowercase()
            }
        if (searchKey.isNotBlank()) {
            viewModelScope.launch {
                newsUseCase(q = searchKey, sortBy = sortingKey).fold(
                    onSuccess = { articles ->
                        _searchUIState.update { it.copy(articles = articles) }
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
}