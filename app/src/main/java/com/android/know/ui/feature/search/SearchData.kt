package com.android.know.ui.feature.search

import com.android.know.domain.entity.ArticleEntity
import com.android.know.ui.components.sorting.ArticleSorting

data class SearchData(
    val searchValue: String = "",
    val q: String = "",
    val articles: List<ArticleEntity> = listOf(),
    val selectedSorting: ArticleSorting? = null
)
