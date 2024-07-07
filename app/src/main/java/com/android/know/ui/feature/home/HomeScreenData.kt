package com.android.know.ui.feature.home

import com.android.know.domain.entity.ArticleEntity
import com.android.know.ui.components.category.ArticleCategories

data class HomeScreenData(
    val articles: List<ArticleEntity> = listOf(),
    val selectedCategory: ArticleCategories = ArticleCategories.GENERAL,
    val savedArticles: Articles = Articles()
)

class Articles(val data: List<ArticleEntity> = listOf())
