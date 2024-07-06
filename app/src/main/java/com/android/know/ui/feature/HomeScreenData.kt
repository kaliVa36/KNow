package com.android.know.ui.feature

import com.android.know.domain.entity.ArticleEntity

data class HomeScreenData(
    val articles: List<ArticleEntity> = listOf()
)
