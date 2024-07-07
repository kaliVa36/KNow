package com.android.know.ui.feature.home

import com.android.know.domain.entity.ArticleEntity
import com.android.know.ui.components.category.ArticleCategories

data class HomeScreenData(
    val articles: List<ArticleEntity> = listOf(),
    val selectedCategory: ArticleCategories = ArticleCategories.GENERAL,
    val savedArticles: Articles = Articles(),
    val listSize: Int = 0,
    val pageCounter: Int = 0,
    val isLastPage: Boolean = false
)

class Articles(val data: List<ArticleEntity> = listOf())
