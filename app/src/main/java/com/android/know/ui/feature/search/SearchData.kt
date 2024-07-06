package com.android.know.ui.feature.search

import com.android.know.domain.entity.ArticleEntity

data class SearchData(
    val searchValue: String = "",
    val q: String = "",
    val articles: List<ArticleEntity> = listOf(),
)
