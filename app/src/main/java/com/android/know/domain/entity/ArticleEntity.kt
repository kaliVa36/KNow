package com.android.know.domain.entity

data class ArticleEntity(
    val id: String = "",
    val source: SourceEntity = SourceEntity(),
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = ""
)

data class SourceEntity(
    val id: String = "",
    val name: String = ""
)
