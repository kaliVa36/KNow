package com.android.know.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.know.domain.usecase.TopHeadlinesUseCase
import com.android.know.ui.components.category.ArticleCategories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val topHeadlinesUseCase: TopHeadlinesUseCase,
) : ViewModel() {
    private val _homeScreenData = MutableStateFlow(HomeScreenData())
    val homeScreenData = _homeScreenData.asStateFlow()

    init {
        getTopHeadlines()
    }

    private fun getTopHeadlines() {
        viewModelScope.launch {
            topHeadlinesUseCase(_homeScreenData.value.selectedCategory.name.lowercase()).fold(
                onSuccess = { articles ->
                    _homeScreenData.update { it.copy(articles = articles) }
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
}