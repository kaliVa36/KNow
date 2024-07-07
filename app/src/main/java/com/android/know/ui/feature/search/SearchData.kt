package com.android.know.ui.feature.search

import com.android.know.domain.entity.ArticleEntity
import com.android.know.ui.components.sorting.ArticleSorting

data class SearchData(
    val searchValue: String = "",
    val q: String = "",
    val articles: List<ArticleEntity> = listOf(),
    val selectedSorting: ArticleSorting? = null,
    val listSize: Int = 0,
    val pageCounter: Int = 1,
    val isLastPage: Boolean = false
)
