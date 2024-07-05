package com.android.know.domain.entity

data class ArticleEntity(
    val source: SourceEntity,
    val author: String,
    val title: String,
    val description: String,
    val url: String
)

data class SourceEntity(
    val id: String,
    val name: String
)
