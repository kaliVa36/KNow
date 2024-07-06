package com.android.know.ui.feature

import com.android.know.domain.entity.ArticleEntity
import com.android.know.ui.components.category.ArticleCategories

data class HomeScreenData(
    val articles: List<ArticleEntity> = listOf(),
    val selectedCategory: ArticleCategories = ArticleCategories.GENERAL,
)
