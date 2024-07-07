package com.android.know.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val roomId: Int = 0,
    val id: String = "",
    val sourceId: String = "",
    val sourceName: String = "",
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = ""
)

data class SourceEntity(
    val id: String = "",
    val name: String = ""
)
