package com.android.know.ui.feature.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SearchViewModel: ViewModel() {
    private val _searchUIState = MutableStateFlow(SearchData())
    val searchUIState = _searchUIState.asStateFlow()

    fun onValueChange(value: String) {
        _searchUIState.update { it.copy(searchValue = value) }
    }

    fun onSearch() {
        // TODO implement search by key or phrase
    }
}